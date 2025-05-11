package bl;


import Model.TourDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TourService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8080/tours";

    public TourDto[] fetchTours() {
        ResponseEntity<TourDto[]> response = restTemplate.getForEntity(API_URL, TourDto[].class);
        return response.getBody();
    }
    public TourDto createTour(TourDto tour) {
        tour.setId(null);
        return restTemplate.postForObject(API_URL, tour, TourDto.class);
    }
    public TourDto updateTour(TourDto updatedTour) {
        String url = API_URL + "/" + updatedTour.getId();  // Construct the URL with the specific ID
        restTemplate.put(url, updatedTour);  // Perform the PUT request
        return updatedTour;  // Return the updated tour (or you could fetch it again after the update if needed)
    }





}
