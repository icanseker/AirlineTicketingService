package com.airline.ticketing.model.passenger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassengerSeatType {

    @JsonProperty(value = "code", required = true)
    private final String code;

    @JsonProperty(value = "definition", required = true)
    private final String definition;

    public PassengerSeatType(String code, String definition) {
        this.code = code;
        this.definition = definition;
    }

    public String code() {
        return code;
    }

    public String definition() {
        return definition;
    }

    @Override
    public String toString() {
        return "Seat type " + code() + ", " + definition();
    }
}
