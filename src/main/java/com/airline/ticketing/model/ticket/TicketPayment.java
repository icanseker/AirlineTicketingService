package com.airline.ticketing.model.ticket;

import java.time.ZonedDateTime;

public final class TicketPayment {

    private final ZonedDateTime paymentDateTime;
    private final String cardNumber;
    private final double amount;

    public TicketPayment(ZonedDateTime paymentDateTime, String cardNumber, double amount) {
        this.paymentDateTime = paymentDateTime;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public ZonedDateTime paymentDateTime() {
        return paymentDateTime;
    }

    public String cardNumber() {
        return cardNumber;
    }

    public double amount() {
        return amount;
    }
}
