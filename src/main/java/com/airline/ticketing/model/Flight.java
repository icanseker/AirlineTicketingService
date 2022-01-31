package com.airline.ticketing.model;

import java.time.ZonedDateTime;

public final class Flight {

    private final String code;
    private final AirRoute route;
    private final Airplane airplane;
    private final ZonedDateTime dateTime;

    public Flight(String code, AirRoute route, Airplane airplane, ZonedDateTime dateTime) {
        this.code = code;
        this.route = route;
        this.airplane = airplane;
        this.dateTime = dateTime;
    }

    public String code() {
        return code;
    }

    public AirRoute route() {
        return route;
    }

    public Airplane airPlane() {
        return airplane;
    }

    public ZonedDateTime dateTime() {
        return dateTime;
    }

    public int estimateFlightTime() {
        return (int) Math.round(route().distanceKilometers() / (airPlane().model().averageCruiseSpeed() / 60.0));
    }

    @Override
    public String toString() {
        return "Flight " + code() + " " + route().toString() + " on " + dateTime().toString();
    }
}
