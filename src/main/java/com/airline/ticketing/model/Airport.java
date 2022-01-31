package com.airline.ticketing.model;

public final class Airport {

    private final String IATACode;
    private final String name;
    private final PortLocation location;

    public Airport(String IATACode, String name, PortLocation location) {
        this.IATACode = IATACode;
        this.name = name;
        this.location = location;
    }

    public String IATACode() {
        return IATACode;
    }

    public String name() {
        return name;
    }

    public PortLocation location() {
        return location;
    }

    @Override
    public String toString() {
        return IATACode() + " " + name() + " " + location().toString();
    }
}
