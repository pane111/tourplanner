package com.fhtw.tpserver.rest;

import com.fhtw.tpserver.model.Tour;
import com.fhtw.tpserver.model.TourLog;
import com.fhtw.tpserver.repo.TourLogRepo;
import com.fhtw.tpserver.repo.TourRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LogController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final TourLogRepo logRepository;

    public LogController(TourLogRepo logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<List<TourLog>> getLogsOfTour(@PathVariable Long id) {
        LOGGER.info("Get logs of tour {}", id);
        List<TourLog> logs = logRepository.findByTourId(id);
        return ResponseEntity.ok(logs);
    }
    //Get specific tour log using its own id
    @GetMapping("/logs/specific/{id}")
    public ResponseEntity<TourLog> getLog(@PathVariable Long id) {
        LOGGER.info("Get log {}", id);
        TourLog log = logRepository.findById(id).orElse(null);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/logs")
    public ResponseEntity<Iterable<TourLog>> getAllLogs() {
        LOGGER.info("Get all logs");
        Iterable<TourLog> logs = logRepository.findAll();
        return ResponseEntity.ok(logs);
    }

    @PostMapping("/logs/{id}")
    public ResponseEntity<TourLog> createLog(@RequestBody TourLog log) {
        LOGGER.info("Create log for tour {}", log.getTourId());
        TourLog savedLog = logRepository.save(log);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }


    @PutMapping("/logs/specific/{id}")
    public ResponseEntity<TourLog> updateTour(@PathVariable Long id, @RequestBody TourLog updatedLog) {
        LOGGER.info("Update log with id {}", id);
        return logRepository.findById(id)
                .map(existingLog -> {
                    existingLog.setLogId(updatedLog.getLogId());
                    existingLog.setTourId(updatedLog.getTourId());
                    existingLog.setDate(updatedLog.getDate());
                    existingLog.setTime(updatedLog.getTime());
                    existingLog.setDistance(updatedLog.getDistance());
                    existingLog.setComment(updatedLog.getComment());
                    existingLog.setRating(updatedLog.getRating());
                    existingLog.setDifficulty(updatedLog.getDifficulty());

                    TourLog savedLog = logRepository.save(existingLog);
                    return new ResponseEntity<>(savedLog, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/logs/specific/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteLog(@PathVariable Long id) {
        LOGGER.info("Deleting log with id {}", id);
        TourLog log = logRepository.findById(id).orElse(null);
        if (log != null) {
            logRepository.deleteById(id);
            LOGGER.info("Deleted log with id {}", id);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            LOGGER.warn("Log with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }


    }
}
