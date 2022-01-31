package com.airline.ticketing.api.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightTicketDetails {

    @JsonProperty(value = "flight-code",required = true)
    public String flightCode;

    @JsonProperty(value = "fare-class-code", required = true)
    public char fareClassCode;

    @JsonProperty(value = "seat-type-code", required = true)
    public String seatTypeCode;

    @JsonProperty(value = "card-number", required = true)
    public String cardNumber;

    @JsonProperty(value = "card-ccv", required = true)
    public String cardValidateCode;

    @JsonProperty(value = "passenger-name", required = true)
    public String passengerFullName;
}
