package com.airline.ticketing.api;

import com.airline.ticketing.api.request.model.AirportDetails;
import com.airline.ticketing.api.response.BasicResponse;
import com.airline.ticketing.model.Airport;
import com.airline.ticketing.model.location.Timezone;
import com.airline.ticketing.model.location.City;
import com.airline.ticketing.model.location.Country;
import com.airline.ticketing.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/location")
public final class LocationAPI {

    private final LocationService locationService;

    @Autowired
    public LocationAPI(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping(path = "/add/city")
    public BasicResponse addCity(@RequestBody City city) {

        BasicResponse response = new BasicResponse();

        try {
            this.locationService.addCity(city.code(), city.name());
            response.setMessage("City added.");
            response.addProperty("Code", city.code());
            response.addProperty("Name", city.name());
        } catch (Exception e) {
            response.setMessage("City could not added.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @PostMapping(path = "/add/country")
    public BasicResponse addCountry(@RequestBody Country country) {

        BasicResponse response = new BasicResponse();

        try {
            this.locationService.addCountry(country.alpha3Code(), country.stateName());
            response.setMessage("Country added.");
            response.addProperty("Code", country.alpha3Code());
            response.addProperty("State Name", country.stateName());
        } catch (Exception e) {
            response.setMessage("Country could not added.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @PostMapping(path = "/add/timezone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BasicResponse defineTimezone(@RequestBody Timezone timezone) {

        BasicResponse response = new BasicResponse();

        try {
            this.locationService.defineTimezone(timezone.id());
            response.setMessage("Timezone defined.");
            response.addProperty("Id", timezone.id());
        } catch (Exception e) {
            response.setMessage("Timezone could not defined.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @PostMapping("/add/airport")
    public BasicResponse pinAirport(@RequestBody AirportDetails details) {

        BasicResponse response = new BasicResponse();
        Airport airport;

        try {
            airport = this.locationService.pinAirport(
                    details.IATACode,
                    details.name,
                    details.countryCode,
                    details.cityCode,
                    details.longitude,
                    details.latitude,
                    details.timeZoneId
            );
            response.setMessage("New Airport pinned.");
            response.addProperty("IATA Code", airport.IATACode());
            response.addProperty("Name", airport.name());
            response.addProperty("City, Country", airport.location().toString());
        } catch (Exception e) {
            response.setMessage("New Airport could not be pinned.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @GetMapping("/airports")
    public List<String> getAirports() {
        return locationService.getAirports()
                .stream()
                .map(Airport::toString)
                .collect(Collectors.toList());
    }

    @GetMapping("/{country-code}/airports")
    public List<String> getAirports(@PathVariable(value = "country-code") String countryCode) {
        return locationService.getAirports(countryCode.toUpperCase(Locale.ENGLISH))
                .stream()
                .map(Airport::toString)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/airports/{iata}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> getAirport(@PathVariable(value = "iata") String airportCode) {
        return Collections.singleton(locationService.getAirport(airportCode.toUpperCase(Locale.ENGLISH)).toString());
    }
}
