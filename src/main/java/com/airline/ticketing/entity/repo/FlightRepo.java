package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.FlightEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Qualifier("flight-r")
public interface FlightRepo extends CrudRepository<FlightEntity, String> {
    FlightEntity findByCode(String code);
    List<FlightEntity> findAllByRoute_RouteCode(String routeCode);
    List<FlightEntity> findAllByRoute_DeparturePort_IATACodeAndRoute_LandingPort_IATACode(String departureIATA, String landingIATA);
    List<FlightEntity> findAllByRoute_DeparturePort_Location_City_CodeAndRoute_LandingPort_Location_City_Code(String departureCityCode, String landingCityCode);
}
