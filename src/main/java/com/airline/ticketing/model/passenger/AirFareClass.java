package com.airline.ticketing.model.passenger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirFareClass {

    @JsonProperty(value = "code", required = true)
    private final char code;

    @JsonProperty(value = "correspond", required = true)
    private final String correspond;

    @JsonProperty(value = "coeff", required = true)
    private final double priceCoefficient;

    public AirFareClass(char code, String correspond, double priceCoefficient) {
        this.code = code;
        this.correspond = correspond;
        this.priceCoefficient = priceCoefficient;
    }

    public char code() {
        return code;
    }

    public String correspond() {
        return correspond;
    }

    public double priceCoefficient() {
        return priceCoefficient;
    }

    @Override
    public String toString() {
        return "Air fare class " + code() + ", " + correspond();
    }
}
