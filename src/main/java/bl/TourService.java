package bl;

import Model.Coordinate;
import Model.SendableTour;
import Model.TourDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TourService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8080/tours";
    private final String ORS_URL = "http://localhost:8080/ors";
    Logger logger = LogManager.getLogger(TourService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public TourDto[] fetchTours() {
        try {
            ResponseEntity<TourDto[]> response = restTemplate.getForEntity(API_URL, TourDto[].class);
            logger.info(response.getBody());
            return response.getBody();
        } catch (Exception e) {
            logger.error(e);
        }
        return new TourDto[0];
    }
    public TourDto fetchTour(Long id) {
        try {
            ResponseEntity<SendableTour> response = restTemplate.getForEntity(API_URL+"/"+id, SendableTour.class);
            logger.info(response.getBody());
            SendableTour st = response.getBody();
            TourDto tourDto = new TourDto(st);
            logger.info("Fetched tour: " + tourDto.getName() + ", coordinates - from: " + tourDto.getFromCoord().orsFormat() + ", to: "+tourDto.getToCoord().orsFormat());
            return tourDto;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    public SendableTour createTour(TourDto tour) {
        try {
            tour.setId(null);

            if (!populateCoordinates(tour)) {
                logger.error("Failed to populate coordinates for tour creation");
                return null;
            }
            calculateDistanceAndTime(tour);
            SendableTour t = new SendableTour();
            t.convertRegTour(tour);
            logger.info("Sendable tour coords, from: " + t.getFrom_coords() + ", to: " + t.getTo_coords());
            return restTemplate.postForObject(API_URL, t, SendableTour.class);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    public void updateTour(TourDto updatedTour) {
        try {
            if (!populateCoordinates(updatedTour)) {
                logger.error("Failed to populate coordinates for tour update");
                return;
            }
            calculateDistanceAndTime(updatedTour);

            String url = API_URL + "/" + updatedTour.getId();
            logger.info("Update url: " + url);
            restTemplate.put(url, updatedTour);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void deleteTour(TourDto tour) {
        try {
            String url = API_URL + "/" + tour.getId();
            restTemplate.delete(url);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private boolean populateCoordinates(TourDto tour) {
        try {
            Coordinate fromCoord = geocode(tour.getFrom());
            logger.info("From coordinates: " + fromCoord.orsFormat());
            if (fromCoord == null) return false;
            tour.setFromCoord(fromCoord);
            Coordinate toCoord = geocode(tour.getTo());
            logger.info("To coordinates: " + toCoord.orsFormat());
            if (toCoord == null) return false;
            tour.setToCoord(toCoord);

            return true;
        } catch (Exception e) {
            logger.error("populateCoordinates error: ", e);
            return false;
        }
    }

    private Coordinate geocode(String location) {
        try {
            String url = ORS_URL + "/geocode?text=" + location;
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            logger.info("GET GEOCODE response: " + response);

            if (response == null) return null;
            JsonNode features = response.get("features");
            if (features == null || !features.isArray() || features.size() == 0) {
                logger.error("No geocode features found for location: " + location);
                return null;
            }

            JsonNode bestMatch = features.get(0);
            String name = bestMatch.path("properties").path("name").asText();
            JsonNode coordNode = bestMatch.path("geometry").path("coordinates");
            if (!coordNode.isArray() || coordNode.size() != 2) {
                logger.error("Invalid coordinates returned for location: " + location);
                return null;
            }

            double lon = coordNode.get(0).asDouble();
            double lat = coordNode.get(1).asDouble();
            logger.info("Geocoded " + location + " to (lat=" + lat + ", lon=" + lon + ")");


            return new Coordinate(lat, lon);

        } catch (Exception e) {
            logger.error("Geocode API error for location '" + location + "'", e);
            return null;
        }
    }

    public JsonNode setMapData(TourDto tour) {

        try {
            Coordinate fromC = tour.getFromCoord();
            Coordinate toC = tour.getToCoord();

            if (fromC == null || toC == null) {
                logger.error("Coordinates missing for distance calculation");
                return null;
            }

            String directionUrl = ORS_URL + "/directions?start=" + fromC.orsFormat() + "&end=" + toC.orsFormat();
            logger.info("Get directions url: " + directionUrl);
            JsonNode directionResponse = restTemplate.getForObject(directionUrl, JsonNode.class);
            return directionResponse;

        } catch (Exception e) {
            logger.error("setMapData error: ", e);
        }
        return null;
    }



    private void calculateDistanceAndTime(TourDto tour) {
        try {
            Coordinate fromC = tour.getFromCoord();
            Coordinate toC = tour.getToCoord();

            if (fromC == null || toC == null) {
                logger.error("Coordinates missing for distance calculation");
                return;
            }

            String directionUrl = ORS_URL + "/directions?start=" + fromC.orsFormat() + "&end=" + toC.orsFormat();
            logger.info("Get directions url: " + directionUrl);
            JsonNode directionResponse = restTemplate.getForObject(directionUrl, JsonNode.class);
            if (directionResponse == null) {
                logger.error("Directions API returned null");
                return;
            }

            JsonNode features = directionResponse.get("features");
            if (features != null && features.isArray() && features.size() > 0) {
                JsonNode summary = features.get(0).path("properties").path("summary");
                double distance = summary.path("distance").asDouble();
                double duration = summary.path("duration").asDouble();
                logger.info("Extracted data: Distance - " + distance + " Duration - " + duration);
                tour.setDistance(distance / 1000); // Convert to km
                tour.setEstimatedTime(String.valueOf(duration));
            } else {
                logger.error("No features found in directions API response");
            }
        } catch (Exception e) {
            logger.error("calculateDistanceAndTime error: ", e);
        }
    }
}