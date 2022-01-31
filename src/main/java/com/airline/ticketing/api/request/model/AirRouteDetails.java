package com.airline.ticketing.api.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirRouteDetails {

    @JsonProperty(value = "code", required = true)
    public String code;

    @JsonProperty(value = "departure-iata", required = true)
    public String departureIATA;

    @JsonProperty(value = "landing-iata", required = true)
    public String landingIATA;

    @JsonProperty(value = "distance", required = true)
    public int distance;
}
