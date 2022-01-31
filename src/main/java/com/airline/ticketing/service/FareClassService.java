package com.airline.ticketing.service;

import com.airline.ticketing.entity.passenger.FareClassEntity;
import com.airline.ticketing.entity.repo.FareClassRepo;
import com.airline.ticketing.model.passenger.AirFareClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FareClassService {

    private final FareClassRepo fareClassRepo;

    @Autowired
    public FareClassService(@Qualifier("fare-class-r") FareClassRepo fareClassRepo) {
        this.fareClassRepo = fareClassRepo;
    }

    public void add(char code, String correspond, double priceCoefficient) throws Exception {

        FareClassEntity entity = this.fareClassRepo.findByCode(code);
        if (entity != null) {
            throw new Exception("Fare class is already exist with given code: " + code + " [with name of '" + entity.globalForm().correspond() + "']");
        }
        this.fareClassRepo.save(new FareClassEntity(code, correspond, priceCoefficient)).globalForm();
    }

    public List<AirFareClass> all() {

        List<AirFareClass> airFareClasses = new ArrayList<>();
        this.fareClassRepo.findAll().forEach(fareClassEntity -> airFareClasses.add(fareClassEntity.globalForm()));

        return airFareClasses;
    }
}
