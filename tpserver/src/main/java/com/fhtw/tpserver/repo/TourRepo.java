package com.fhtw.tpserver.repo;

import com.fhtw.tpserver.model.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepo extends CrudRepository<Tour, Long> {

}
