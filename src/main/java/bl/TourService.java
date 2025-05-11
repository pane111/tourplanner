package bl;


import Model.TourDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TourService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8080/tours";
    Logger logger = LogManager.getLogger(TourService.class);
    public TourDto[] fetchTours() {
        try {
            ResponseEntity<TourDto[]> response = restTemplate.getForEntity(API_URL, TourDto[].class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.error(e);
        }
        return new TourDto[0];
    }
    public TourDto createTour(TourDto tour) {
        try {
            tour.setId(null);
            return restTemplate.postForObject(API_URL, tour, TourDto.class);
        }
        catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    public TourDto updateTour(TourDto updatedTour) {
        try {
            String url = API_URL + "/" + updatedTour.getId();
            restTemplate.put(url, updatedTour);
            return updatedTour;
        }
        catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    public void deleteTour(TourDto tour) {
        try {
            String url = API_URL + "/" + tour.getId();
            restTemplate.delete(url);

        }
        catch (Exception e) {
            logger.error(e);
        }
    }





}
