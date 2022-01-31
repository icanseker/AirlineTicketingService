package com.airline.ticketing.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class City {

    @JsonProperty(value = "code", required = true)
    private final String code;

    @JsonProperty(value = "name", required = true)
    private final String name;

    public City(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }
}
