package com.fhtw.tpserver.repo;

import com.fhtw.tpserver.model.TourLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourLogRepo extends CrudRepository<TourLog, Long> {
    List<TourLog> findByTourId(Long tourId);
}
