package com.airline.ticketing.api.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportDetails {

    @JsonProperty(value = "iata", required = true)
    public String IATACode;

    @JsonProperty(value = "name", required = true)
    public String name;

    @JsonProperty(value = "country-code", required = true)
    public String countryCode;

    @JsonProperty(value = "city-code", required = true)
    public String cityCode;

    @JsonProperty(value = "longitude", required = true)
    public String longitude;

    @JsonProperty(value = "latitude", required = true)
    public String latitude;

    @JsonProperty(value = "zone", required = true)
    public String timeZoneId;
}
