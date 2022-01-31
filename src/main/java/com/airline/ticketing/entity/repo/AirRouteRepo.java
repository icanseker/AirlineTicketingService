package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.AirRouteEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("air-route-r")
public interface AirRouteRepo extends CrudRepository<AirRouteEntity, String> {
    AirRouteEntity findByRouteCode(String code);
    List<AirRouteEntity> findAllByDeparturePort_IATACode(String iata);
    List<AirRouteEntity> findAllByLandingPort_IATACode(String iata);
    List<AirRouteEntity> findAllByDeparturePort_IATACodeAndLandingPort_IATACode(String departureIATA, String landingIATA);
}
