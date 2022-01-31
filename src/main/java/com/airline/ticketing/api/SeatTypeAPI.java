package com.airline.ticketing.api;

import com.airline.ticketing.api.response.BasicResponse;
import com.airline.ticketing.model.passenger.PassengerSeatType;
import com.airline.ticketing.service.PassengerSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seats")
public class SeatTypeAPI {

    private final PassengerSeatService passengerSeatService;

    @Autowired
    public SeatTypeAPI(PassengerSeatService passengerSeatService) {
        this.passengerSeatService = passengerSeatService;
    }

    @PostMapping("/type/add")
    public BasicResponse addSeatType(@RequestBody PassengerSeatType seatType) {

        BasicResponse response = new BasicResponse();

        try {
            this.passengerSeatService.addSeatType(seatType.code(), seatType.definition());
            response.setMessage("Seat type added.");
            response.addProperty("Code", seatType.code());
            response.addProperty("Definition", seatType.definition());
        } catch (Exception e) {
            response.setMessage("Seat type could not added.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @GetMapping("/types")
    public List<String> getSeatTypes() {
        return passengerSeatService.allSeatTypes()
                .stream()
                .map(PassengerSeatType::toString)
                .collect(Collectors.toList());
    }
}
