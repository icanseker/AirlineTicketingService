package com.airline.ticketing.model.ticket;

public final class PassengerDetail {

    private final String fullName;

    public PassengerDetail(String fullName) {
        this.fullName = fullName;
    }

    public String fullName() {
        return fullName;
    }
}
