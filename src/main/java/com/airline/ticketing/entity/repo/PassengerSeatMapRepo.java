package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.passenger.PassengerSeatMapEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("passenger-seat-map-r")
public interface PassengerSeatMapRepo extends CrudRepository<PassengerSeatMapEntity, String> {
    PassengerSeatMapEntity findByMapCode(String mapId);
}
