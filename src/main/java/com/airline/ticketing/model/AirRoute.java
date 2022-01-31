package com.airline.ticketing.model;

public final class AirRoute {

    private final String code;
    private final Airport departurePort;
    private final Airport landingPort;

    private final int distance; // in km

    public AirRoute(String code, Airport departurePort, Airport landingPort, int distance) {
        this.code = code;
        this.departurePort = departurePort;
        this.landingPort = landingPort;
        this.distance = distance;
    }

    public String code() {
        return code;
    }

    public Airport departurePort() {
        return departurePort;
    }

    public Airport landingPort() {
        return landingPort;
    }

    public int distanceKilometers() {
        return distance;
    }

    public int distanceMiles() {
        return (int) Math.round(distanceKilometers() * 0.621);
    }

    @Override
    public String toString() {
        return distanceMiles() + " miles-long Air Route " + code() + " [from " + departurePort().IATACode() + " to " + landingPort().IATACode() + "]";
    }
}
