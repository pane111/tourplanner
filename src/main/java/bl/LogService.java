package bl;

import Model.TourDto;
import Model.TourLogDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LogService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8080/logs";
    Logger logger = LogManager.getLogger(TourService.class);
    public TourLogDto[] fetchTours() {
        try {
            ResponseEntity<TourLogDto[]> response = restTemplate.getForEntity(API_URL, TourLogDto[].class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.error(e);
        }
        return new TourLogDto[0];
    }

    public TourLogDto createLog(TourLogDto log) {
        try {
            log.setLogId(null);
            return restTemplate.postForObject(API_URL, log, TourLogDto.class);
        }
        catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    public TourLogDto updateLog(TourLogDto updatedLog) {
        try {
            String url = API_URL + "/specific/" + updatedLog.getLogId();
            restTemplate.put(url, updatedLog);
            return updatedLog;
        }
        catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    public void deleteLog(TourLogDto log) {
        try {
            String url = API_URL + "/specific/" + log.getLogId();
            restTemplate.delete(url);

        }
        catch (Exception e) {
            logger.error(e);
        }
    }
}
