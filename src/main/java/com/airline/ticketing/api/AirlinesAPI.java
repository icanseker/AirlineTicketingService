package com.airline.ticketing.api;

import com.airline.ticketing.api.response.BasicResponse;
import com.airline.ticketing.model.Airlines;
import com.airline.ticketing.service.AirlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/airlines")
public class AirlinesAPI {

    private final AirlinesService airlinesService;

    @Autowired
    public AirlinesAPI(AirlinesService airlinesService) {
        this.airlinesService = airlinesService;
    }

    @PostMapping("/add")
    public BasicResponse add(@RequestBody Airlines airlines) {

        BasicResponse response = new BasicResponse();

        try {
            this.airlinesService.add(airlines.IATACode(), airlines.name());
            response.setMessage("Airlines added.");
            response.addProperty("IATA Code", airlines.IATACode());
            response.addProperty("Name", airlines.name());
        } catch (Exception e) {
            response.setMessage("Airlines could not added.");
            response.addProperty("Error message", e.getMessage());
        }

        return response;
    }

    @GetMapping
    public List<String> getAirports() {
        return airlinesService.all()
                .stream()
                .map(Airlines::toString)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{iata-code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> getAirports(@PathVariable("iata-code") String IATACode) {
        return Collections.singleton(airlinesService.find(IATACode.toUpperCase(Locale.ENGLISH)).toString());
    }
}
