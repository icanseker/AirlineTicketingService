package com.airline.ticketing.model;

import com.airline.ticketing.model.location.City;
import com.airline.ticketing.model.location.Country;
import com.airline.ticketing.model.location.Timezone;

public final class PortLocation {

    private final Country country;
    private final City city;

    private final String longitude;
    private final String latitude;

    private final Timezone timeZone;

    public PortLocation(Country country, City city, String longitude, String latitude, Timezone timeZone) {
        this.country = country;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeZone = timeZone;
    }

    public Country country() {
        return country;
    }

    public City city() {
        return city;
    }

    public String longitude() {
        return longitude;
    }

    public String latitude() {
        return latitude;
    }

    public Timezone timeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return city().name() + "," + country().stateName();
    }

    public String fullDescription() {
        return toString() + " [" + longitude + ";" + latitude + "]";
    }
}
