package com.airline.ticketing.entity.ticket;

import com.airline.ticketing.model.ticket.TicketPayment;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name = "TicketPayment")
@Table(name = "ticket_payments")
public class TicketPaymentEntity {

    @Id
    @GeneratedValue
    @Column(name = "payment_uid", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "date_time", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime paymentDateTime;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "amount", nullable = false)
    private double amount;

    public TicketPaymentEntity() {
    }

    public TicketPaymentEntity(ZonedDateTime paymentDateTime, String cardNumber, double amount) {
        this.paymentDateTime = paymentDateTime;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public TicketPaymentEntity(TicketPayment ticketPayment) {
        this.paymentDateTime = ticketPayment.paymentDateTime();
        this.cardNumber = ticketPayment.cardNumber();
        this.amount = ticketPayment.amount();
    }

    public TicketPaymentEntity setPaymentDateTime(ZonedDateTime paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
        return this;
    }

    public TicketPaymentEntity setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public TicketPaymentEntity setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public TicketPayment globalForm() {
        return new TicketPayment(this.paymentDateTime, this.cardNumber, amount);
    }
}
