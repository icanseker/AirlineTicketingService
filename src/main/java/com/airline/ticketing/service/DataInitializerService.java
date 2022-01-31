package com.airline.ticketing.service;

import com.airline.ticketing.entity.*;
import com.airline.ticketing.entity.airplane.AirplaneCategoryEntity;
import com.airline.ticketing.entity.airplane.AirplaneModelEntity;
import com.airline.ticketing.entity.location.CountryEntity;
import com.airline.ticketing.entity.location.TimezoneEntity;
import com.airline.ticketing.entity.passenger.FareClassEntity;
import com.airline.ticketing.entity.passenger.PassengerSeatMapEntity;
import com.airline.ticketing.entity.passenger.SeatTypeEntity;
import com.airline.ticketing.entity.repo.*;
import com.airline.ticketing.model.Airlines;
import com.airline.ticketing.model.Airport;
import com.airline.ticketing.model.location.Timezone;
import com.airline.ticketing.model.airplane.AirPlaneCategory;
import com.airline.ticketing.model.location.Country;
import com.airline.ticketing.model.passenger.AirFareClass;
import com.airline.ticketing.model.passenger.PassengerSeatMap;
import com.airline.ticketing.model.passenger.PassengerSeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public final class DataInitializerService {

    private final SeatTypeRepo seatTypeRepo;
    private final FareClassRepo fareClassRepo;
    private final CountryRepo countryRepo;
    private final TimezoneRepo timeZoneRepo;
    private final AirportRepo airportRepo;
    private final AirRouteRepo airRouteRepo;
    private final AirlinesRepo airlinesRepo;
    private final AirplaneCategoryRepo airplaneCategoryRepo;
    private final PassengerSeatMapRepo passengerSeatMapRepo;
    private final AirplaneModelRepo airplaneModelRepo;
    private final AirplaneRepo airplaneRepo;
    private final FlightRepo flightRepo;

    @Autowired
    public DataInitializerService(
            @Qualifier("seat-type-r") SeatTypeRepo seatTypeRepo,
            @Qualifier("fare-class-r") FareClassRepo fareClassRepo,
            @Qualifier("country-r") CountryRepo countryRepo,
            @Qualifier("timezone-r") TimezoneRepo timeZoneRepo,
            @Qualifier("airport-r") AirportRepo airportRepo,
            @Qualifier("air-route-r") AirRouteRepo directRouteRepo,
            @Qualifier("airlines-r") AirlinesRepo airlinesRepo,
            @Qualifier("airplane-cat-r") AirplaneCategoryRepo airplaneCategoryRepo,
            @Qualifier("passenger-seat-map-r") PassengerSeatMapRepo passengerSeatMapRepo,
            @Qualifier("airplane-model-r") AirplaneModelRepo airplaneModelRepo,
            @Qualifier("airplane-r") AirplaneRepo airplaneRepo,
            @Qualifier("flight-r") FlightRepo flightRepo
    ) {
        this.seatTypeRepo = seatTypeRepo;
        this.fareClassRepo = fareClassRepo;
        this.countryRepo = countryRepo;
        this.timeZoneRepo = timeZoneRepo;
        this.airportRepo = airportRepo;
        this.airRouteRepo = directRouteRepo;
        this.airlinesRepo = airlinesRepo;
        this.airplaneCategoryRepo = airplaneCategoryRepo;
        this.passengerSeatMapRepo = passengerSeatMapRepo;
        this.airplaneModelRepo = airplaneModelRepo;
        this.airplaneRepo = airplaneRepo;
        this.flightRepo = flightRepo;
    }

    public void add(PassengerSeatType seatType) {
        this.seatTypeRepo.save(new SeatTypeEntity(seatType));
    }

    public void add(AirFareClass fareClass) {
        this.fareClassRepo.save(new FareClassEntity(fareClass));
    }

    public void add(Country country) {
        this.countryRepo.save(new CountryEntity(country));
    }

    public void add(Timezone timeZone) {
        this.timeZoneRepo.save(new TimezoneEntity(timeZone)).globalForm();
    }

    public void add(Airport airport) {
        this.airportRepo.save(new AirportEntity(airport));
    }

    public void add(String routeCode, String departureIATA, String landingIATA, int distance) {

        AirportEntity departure = this.airportRepo.findByIATACode(departureIATA);
        AirportEntity landing = this.airportRepo.findByIATACode(landingIATA);

        this.airRouteRepo.save(new AirRouteEntity(routeCode, departure, landing, distance));
    }

    public void add(Airlines airlines) {
        this.airlinesRepo.save(new AirlinesEntity(airlines));
    }

    public void add(AirPlaneCategory airPlaneCategory) {
        this.airplaneCategoryRepo.save(new AirplaneCategoryEntity(airPlaneCategory));
    }

    public void add(PassengerSeatMap passengerSeatMap) {
        this.passengerSeatMapRepo.save(new PassengerSeatMapEntity(passengerSeatMap));
    }

    public void add(String ICAOCode, String modelDef, String airplaneCategoryCode, String passengerSeatMapCode, int averageCruiseSpeed) {

        this.airplaneModelRepo.save(new AirplaneModelEntity(
                ICAOCode,
                modelDef,
                airplaneCategoryRepo.findByCode(airplaneCategoryCode),
                passengerSeatMapRepo.findByMapCode(passengerSeatMapCode),
                averageCruiseSpeed
        )).globalForm();
    }

    public void add(String code, String name, String ICAOModelCode, String airlinesIATACode, String passengerSeatMapCode) {

        this.airplaneRepo.save(new AirplaneEntity(
                code,
                name,
                airplaneModelRepo.findByICAOCode(ICAOModelCode),
                airlinesRepo.findByIATACode(airlinesIATACode),
                passengerSeatMapCode == null ? null : passengerSeatMapRepo.findByMapCode(passengerSeatMapCode))).globalForm();
    }

    public void add(String routeCode, String airplaneCode, ZonedDateTime dateTime, double rawTicketPrice) {

        this.flightRepo.save(new FlightEntity(
                this.airRouteRepo.findByRouteCode(routeCode),
                this.airplaneRepo.findByCode(airplaneCode),
                dateTime,
                rawTicketPrice
        ));
    }
}
