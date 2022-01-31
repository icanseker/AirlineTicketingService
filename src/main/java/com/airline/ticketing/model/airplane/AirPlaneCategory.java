package com.airline.ticketing.model.airplane;

public class AirPlaneCategory {

    private final String code;
    private final String name;

    public AirPlaneCategory(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
