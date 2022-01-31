package com.airline.ticketing.api;

import com.airline.ticketing.api.response.BasicResponse;
import com.airline.ticketing.model.passenger.AirFareClass;
import com.airline.ticketing.service.FareClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/airfare")
public class FareClassAPI {

    private final FareClassService fareClassService;

    @Autowired
    public FareClassAPI(FareClassService fareClassService) {
        this.fareClassService = fareClassService;
    }

    @PostMapping("/add")
    public BasicResponse add(@RequestBody AirFareClass airFareClass) {

        BasicResponse response = new BasicResponse();

        try {
            this.fareClassService.add(airFareClass.code(), airFareClass.correspond(), airFareClass.priceCoefficient());
            response.setMessage("Fare class added.");
            response.addProperty("Code", String.valueOf(airFareClass.code()));
            response.addProperty("Correspond", airFareClass.correspond());
            response.addProperty("Price Coefficient", String.valueOf(airFareClass.priceCoefficient()));
        } catch (Exception e) {
            response.setMessage("Fare class could not added.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @GetMapping
    public List<String> getClasses() {
        return fareClassService.all()
                .stream()
                .map(AirFareClass::toString)
                .collect(Collectors.toList());
    }
}
