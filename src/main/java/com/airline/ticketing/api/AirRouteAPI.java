package com.airline.ticketing.api;

import com.airline.ticketing.api.request.model.AirRouteDetails;
import com.airline.ticketing.api.response.BasicResponse;
import com.airline.ticketing.model.AirRoute;
import com.airline.ticketing.service.AirRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/air-route")
public class AirRouteAPI {

    private final AirRouteService airRouteService;

    @Autowired
    public AirRouteAPI(AirRouteService airRouteService) {
        this.airRouteService = airRouteService;
    }

    @PostMapping("/add")
    public BasicResponse add(@RequestBody AirRouteDetails details) {

        BasicResponse response = new BasicResponse();

        try {
            this.airRouteService.add(details.code, details.departureIATA, details.landingIATA, details.distance);
            response.setMessage("Air Route added.");
            response.addProperty("Route Code", details.code);
            response.addProperty("From/To", details.departureIATA + "/" + details.landingIATA);
        } catch (Exception e) {
            response.setMessage("Air Route could not added.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @GetMapping
    public List<String> getAirRoutes() {
        return airRouteService.all()
                .stream()
                .map(AirRoute::toString)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> findByCode(@PathVariable("code") String routeCode) {

        AirRoute found = airRouteService.findByCode(routeCode.toUpperCase(Locale.ENGLISH));
        if(found != null) {
            return Collections.singleton(found.toString());
        }
        else return Collections.singleton("No air route with code: " + routeCode);
    }

    @PostMapping("/find")
    public List<String> findByPort(@RequestBody(required = false) Map<String, String> searchCriteriaMap) {

        if(searchCriteriaMap == null)
            return Collections.emptyList();

        return airRouteService.findByPort(searchCriteriaMap.get("departure-iata"), searchCriteriaMap.get("landing-iata"))
                .stream()
                .map(AirRoute::toString)
                .collect(Collectors.toList());
    }
}
