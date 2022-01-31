package com.airline.ticketing.service;

import com.airline.ticketing.entity.passenger.SeatTypeEntity;
import com.airline.ticketing.entity.repo.SeatTypeRepo;
import com.airline.ticketing.model.passenger.PassengerSeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerSeatService {

    private final SeatTypeRepo seatTypeRepo;

    @Autowired
    public PassengerSeatService(@Qualifier("seat-type-r") SeatTypeRepo seatTypeRepo) {
        this.seatTypeRepo = seatTypeRepo;
    }

    public void addSeatType(String code, String definition) throws Exception {

        SeatTypeEntity entity = this.seatTypeRepo.findByCode(code);
        if (entity != null) {
            throw new Exception("Passenger Seat Type is already exist with given code: " + code + " [with definition of '" + entity.globalForm().definition() + "']");
        }
        this.seatTypeRepo.save(new SeatTypeEntity(code, definition)).globalForm();
    }

    public List<PassengerSeatType> allSeatTypes() {

        List<PassengerSeatType> seatTypes = new ArrayList<>();
        this.seatTypeRepo.findAll().forEach(seatTypeEntity -> seatTypes.add(seatTypeEntity.globalForm()));

        return seatTypes;
    }
}
