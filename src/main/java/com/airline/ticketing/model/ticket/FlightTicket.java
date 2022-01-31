package com.airline.ticketing.model.ticket;

import com.airline.ticketing.model.Flight;

public final class FlightTicket {

    private final String ticketCode;
    private final Flight flight;
    private final TicketPayment payment;
    private final PassengerDetail passengerDetail;
    private final String seatSpotCode;

    public FlightTicket(String ticketCode, Flight flight, TicketPayment payment, PassengerDetail passengerDetail, String seatSpotCode) {
        this.ticketCode = ticketCode;
        this.flight = flight;
        this.payment = payment;
        this.passengerDetail = passengerDetail;
        this.seatSpotCode = seatSpotCode;
    }

    public String ticketCode() {
        return ticketCode;
    }

    public Flight flight() {
        return flight;
    }

    public TicketPayment payment() {
        return payment;
    }

    public PassengerDetail passengerDetail() {
        return passengerDetail;
    }

    public String seatSpotCode() {
        return seatSpotCode;
    }
}
