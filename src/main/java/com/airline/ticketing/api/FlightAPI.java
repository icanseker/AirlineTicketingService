package com.airline.ticketing.api;

import com.airline.ticketing.api.request.model.FlightDetails;
import com.airline.ticketing.api.response.BasicResponse;
import com.airline.ticketing.model.Flight;
import com.airline.ticketing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flight")
public class FlightAPI {

    private final FlightService flightService;

    @Autowired
    public FlightAPI(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public BasicResponse add(@RequestBody FlightDetails details) {

        BasicResponse response = new BasicResponse();
        LocalDateTime dateTime = LocalDateTime.parse(details.dateTime, DateTimeFormatter.ofPattern(details.dateTimeFormat));

        try {

            Flight added = this.flightService.add(
                    details.routeCode,
                    details.airplaneCode,
                    ZonedDateTime.of(dateTime, ZoneId.of(details.timeZone)),
                    details.rawTicketPrice
            );

            response.setMessage("Flight added successfully.");
            response.addProperty("Code", added.code());
            response.addProperty("Date-Time", added.dateTime().toString());
            response.addProperty("Estimated flight time", added.estimateFlightTime() + " minutes");
            response.addProperty("Route", added.route().toString());
            response.addProperty("Airplane", added.airPlane().toString());

        } catch (Exception e) {
            response.setMessage("Flight could not added.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @GetMapping("/find/all")
    public List<String> findFlight() {
        return flightService.all()
                .stream()
                .map(Flight::toString)
                .collect(Collectors.toList());
    }

    @GetMapping("/find/by-route/{route-code}")
    public List<String> findByRoute(@PathVariable("route-code") String routeCode) {
        return flightService.findByRoute(routeCode)
                .stream()
                .map(Flight::toString)
                .collect(Collectors.toList());
    }

    @PostMapping("/find/by-port")
    public List<String> findByPort(@RequestBody Map<String, String> portCodes) {

        if (portCodes == null)
            return Collections.emptyList();

        String departureIATA = portCodes.get("departure-iata");
        String landingIATA = portCodes.get("landing-iata");

        if(departureIATA == null || landingIATA == null)
            return Collections.emptyList();

        return flightService.findByPort(departureIATA, landingIATA)
                .stream()
                .map(Flight::toString)
                .collect(Collectors.toList());
    }

    @PostMapping("/find/by-city")
    public List<String> findByCity(@RequestBody Map<String, String> cityCodes) {

        if (cityCodes == null)
            return Collections.emptyList();

        String departureCode = cityCodes.get("departure-code");
        String landingCode = cityCodes.get("landing-code");

        if(departureCode == null || landingCode == null)
            return Collections.emptyList();

        return flightService.findByCity(departureCode, landingCode)
                .stream()
                .map(Flight::toString)
                .collect(Collectors.toList());
    }
}
