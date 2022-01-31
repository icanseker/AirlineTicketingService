package com.airline.ticketing.service;

import com.airline.ticketing.entity.FlightEntity;
import com.airline.ticketing.entity.repo.AirRouteRepo;
import com.airline.ticketing.entity.repo.AirplaneRepo;
import com.airline.ticketing.entity.repo.FlightRepo;
import com.airline.ticketing.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepo flightRepo;
    private final AirRouteRepo airRouteRepo;
    private final AirplaneRepo airplaneRepo;

    @Autowired
    public FlightService(
            @Qualifier("flight-r") FlightRepo flightRepo,
            @Qualifier("air-route-r") AirRouteRepo airRouteRepo,
            @Qualifier("airplane-r") AirplaneRepo airplaneRepo
    ) {
        this.flightRepo = flightRepo;
        this.airRouteRepo = airRouteRepo;
        this.airplaneRepo = airplaneRepo;
    }

    public Flight add(String routeCode, String airplaneCode, ZonedDateTime zonedDateTime, double rawTicketPrice) {
        return this.flightRepo.save(
                new FlightEntity(
                        airRouteRepo.findByRouteCode(routeCode),
                        airplaneRepo.findByCode(airplaneCode),
                        zonedDateTime,
                        rawTicketPrice
                )
        ).globalForm();
    }

    public List<Flight> all() {

        List<Flight> flights = new ArrayList<>();
        this.flightRepo.findAll().forEach(flightEntity -> flights.add(flightEntity.globalForm()));

        return flights;
    }

    public List<Flight> findByRoute(String routeCode) {

        List<Flight> flights = new ArrayList<>();
        this.flightRepo
                .findAllByRoute_RouteCode(routeCode)
                .forEach(flightEntity -> flights.add(flightEntity.globalForm()));

        return flights;
    }

    public List<Flight> findByPort(String departureIATA, String landingIATA) {

        List<Flight> flights = new ArrayList<>();
        this.flightRepo
                .findAllByRoute_DeparturePort_IATACodeAndRoute_LandingPort_IATACode(departureIATA, landingIATA)
                .forEach(flightEntity -> flights.add(flightEntity.globalForm()));

        return flights;
    }

    public List<Flight> findByCity(String departureCityCode, String landingCityCode) {

        List<Flight> flights = new ArrayList<>();
        this.flightRepo
                .findAllByRoute_DeparturePort_Location_City_CodeAndRoute_LandingPort_Location_City_Code(departureCityCode, landingCityCode)
                .forEach(flightEntity -> flights.add(flightEntity.globalForm()));

        return flights;
    }
}
