package com.airline.ticketing.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Airlines {

    @JsonProperty(value = "iata", required = true)
    private final String IATACode;

    @JsonProperty(value = "name", required = true)
    private final String name;

    public Airlines(String iataCode, String name) {
        IATACode = iataCode;
        this.name = name;
    }

    public String IATACode() {
        return IATACode;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return IATACode() + " " + name();
    }
}
