package com.fhtw.tpserver.rest;

import com.fhtw.tpserver.model.Tour;
import com.fhtw.tpserver.repo.TourRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Optional;

@RestController
public class TourController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final TourRepo tourRepository;

    public TourController(TourRepo tourRepository) {
        this.tourRepository = tourRepository;
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity<Tour> getTour(@PathVariable Long id) {
        LOGGER.info("Get tour with id {}", id);
        Optional<Tour> tour = tourRepository.findById(id);
        return ResponseEntity.of(tour);
    }

    @GetMapping("/tours")
    public ResponseEntity<Iterable<Tour>> getAllTours() {
        LOGGER.info("Get all tours");
        Iterable<Tour> tours = tourRepository.findAll();
        return ResponseEntity.ok(tours);
    }

    @PostMapping("/tours")
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {


        LOGGER.info("Create tour {}", tour.getName());
        LOGGER.info("Coordinates - From: " + tour.getFrom_coords() + " To: " + tour.getTo_coords());
        Tour savedTour = tourRepository.save(tour);
        return new ResponseEntity<>(savedTour, HttpStatus.CREATED);
    }



    @PutMapping("/tours/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour updatedTour) {
        LOGGER.info("Update tour with id {}", id);
        return tourRepository.findById(id)
                .map(existingTour -> {
                    existingTour.setName(updatedTour.getName());
                    existingTour.setFrom_loc(updatedTour.getFrom_loc());
                    existingTour.setTo_loc(updatedTour.getTo_loc());
                    existingTour.setDistance(updatedTour.getDistance());
                    existingTour.setEstimated_time(updatedTour.getEstimated_time());
                    existingTour.setDescription(updatedTour.getDescription());
                    existingTour.setImage(updatedTour.getImage());
                    Tour savedTour = tourRepository.save(existingTour);
                    return new ResponseEntity<>(savedTour, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/tours/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteTour(@PathVariable Long id) {
        LOGGER.info("Deleting tour with id {}", id);
        Tour tour = tourRepository.findById(id).orElse(null);
        if (tour != null) {
            tourRepository.deleteById(id);
            LOGGER.info("Deleted tour with id {}", id);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            LOGGER.warn("Tour with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }


    }

}
