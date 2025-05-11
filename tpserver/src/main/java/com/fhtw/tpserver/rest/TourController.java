package com.fhtw.tpserver.rest;

import com.fhtw.tpserver.model.Tour;
import com.fhtw.tpserver.repo.TourRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TourController {

    private final TourRepo tourRepository;

    public TourController(TourRepo tourRepository) {
        this.tourRepository = tourRepository;
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity<Tour> getTour(@PathVariable Long id) {
        Optional<Tour> tour = tourRepository.findById(id);
        return ResponseEntity.of(tour);
    }

    @GetMapping("/tours")
    public ResponseEntity<Iterable<Tour>> getAllTours() {
        Iterable<Tour> tours = tourRepository.findAll();
        return ResponseEntity.ok(tours);
    }

    @PostMapping("/tours")
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        Tour savedTour = tourRepository.save(tour);
        return new ResponseEntity<>(savedTour, HttpStatus.CREATED);
    }


    //Not working
    @PutMapping("/tours/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour updatedTour) {
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

        Tour tour = tourRepository.findById(id).orElse(null);
        if (tour != null) {
            tourRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }


    }

}
