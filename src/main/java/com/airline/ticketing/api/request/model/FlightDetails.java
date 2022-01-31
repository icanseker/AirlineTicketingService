package com.airline.ticketing.api.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightDetails {

    @JsonProperty(value = "route", required = true)
    public String routeCode;

    @JsonProperty(value = "airplane", required = true)
    public String airplaneCode;

    @JsonProperty(value = "date-time-formatted", required = true)
    public String dateTime;

    @JsonProperty(value = "dt-format", required = true)
    public String dateTimeFormat;

    @JsonProperty(value = "timezone", required = true)
    public String timeZone;

    @JsonProperty(value = "raw-ticket-price", required = true)
    public double rawTicketPrice;
}
