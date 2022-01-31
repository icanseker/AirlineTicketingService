package com.airline.ticketing.model.passenger;

public class PassengerSeat {

    private final String spotCode;
    private final PassengerSeatType seatType;
    private final AirFareClass fareClass;

    public PassengerSeat(String spotCode, PassengerSeatType seatType, AirFareClass fareClass) {
        this.spotCode = spotCode;
        this.seatType = seatType;
        this.fareClass = fareClass;
    }

    public String getSpotCode() {
        return spotCode;
    }

    public PassengerSeatType seatType() {
        return seatType;
    }

    public AirFareClass fareClass() {
        return fareClass;
    }
}
