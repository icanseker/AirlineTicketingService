package com.airline.ticketing.service;

import com.airline.ticketing.entity.AirRouteEntity;
import com.airline.ticketing.entity.repo.AirRouteRepo;
import com.airline.ticketing.entity.repo.AirportRepo;
import com.airline.ticketing.model.AirRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirRouteService {

    private final AirRouteRepo airRouteRepo;
    private final AirportRepo airportRepo;

    @Autowired
    public AirRouteService(@Qualifier("air-route-r") AirRouteRepo airRouteRepo, @Qualifier("airport-r") AirportRepo airportRepo) {
        this.airRouteRepo = airRouteRepo;
        this.airportRepo = airportRepo;
    }

    public void add(String code, String departureIATA, String landingIATA, int distance) throws Exception {

        AirRouteEntity entity = airRouteRepo.findByRouteCode(code);
        if (entity != null) {
            throw new Exception("Route is already exist: " + entity.globalForm().toString());
        }
        this.airRouteRepo.save(
                new AirRouteEntity(
                        code,
                        this.airportRepo.findByIATACode(departureIATA),
                        this.airportRepo.findByIATACode(landingIATA),
                        distance
                )
        );
    }

    public List<AirRoute> all() {

        List<AirRoute> airRoutes = new ArrayList<>();
        this.airRouteRepo.findAll().forEach(airRouteEntity -> airRoutes.add(airRouteEntity.globalForm()));

        return airRoutes;
    }

    public AirRoute findByCode(String code) {
        return this.airRouteRepo.findByRouteCode(code).globalForm();
    }

    public List<AirRoute> findByPort(String departureIATA, String landingIATA) {

        List<AirRoute> airRoutes = new ArrayList<>();

        if(departureIATA != null && landingIATA != null) {
            this.airRouteRepo
                    .findAllByDeparturePort_IATACodeAndLandingPort_IATACode(departureIATA, landingIATA)
                    .forEach(airRouteEntity -> airRoutes.add(airRouteEntity.globalForm()));
        }

        else if(departureIATA != null) {
            this.airRouteRepo
                    .findAllByDeparturePort_IATACode(departureIATA)
                    .forEach(airRouteEntity -> airRoutes.add(airRouteEntity.globalForm()));
        }

        else if(landingIATA != null) {
            this.airRouteRepo
                    .findAllByLandingPort_IATACode(landingIATA)
                    .forEach(airRouteEntity -> airRoutes.add(airRouteEntity.globalForm()));
        }

        return airRoutes;
    }
}
