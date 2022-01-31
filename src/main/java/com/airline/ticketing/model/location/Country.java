package com.airline.ticketing.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Country {

    @JsonProperty(value = "code", required = true)
    private final String alpha3Code;

    @JsonProperty(value = "name", required = true)
    private final String stateName;

    public Country(String alpha3Code, String stateName) {
        this.alpha3Code = alpha3Code;
        this.stateName = stateName;
    }

    public String alpha3Code() {
        return alpha3Code;
    }

    public String stateName() {
        return stateName;
    }
}
