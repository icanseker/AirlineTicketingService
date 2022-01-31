package com.airline.ticketing.entity.ticket;

import com.airline.ticketing.entity.FlightEntity;
import com.airline.ticketing.model.ticket.FlightTicket;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "FlightTicket")
@Table(name = "flight_tickets")
public class FlightTicketEntity {

    @Id
    @GeneratedValue
    @Column(name = "ticket_uid", columnDefinition = "uuid", updatable = false)
    private UUID ticketCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_code", nullable = false, referencedColumnName = "code")
    private FlightEntity flight;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", nullable = false, referencedColumnName = "payment_uid")
    private TicketPaymentEntity payment;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_detail_id", nullable = false, referencedColumnName = "detail_uid")
    private PassengerDetailEntity passengerDetail;

    @Column(name = "seat_spot_code", nullable = false)
    private String seatSpotCode;

    public FlightTicketEntity() {
    }

    public FlightTicketEntity(FlightEntity flight, TicketPaymentEntity payment, PassengerDetailEntity passengerDetail, String seatSpotCode) {
        this.flight = flight;
        this.payment = payment;
        this.passengerDetail = passengerDetail;
        this.seatSpotCode = seatSpotCode;
    }

    public FlightTicket globalForm() {
        return new FlightTicket(this.ticketCode.toString(), this.flight.globalForm(), this.payment.globalForm(), this.passengerDetail.globalForm(), this.seatSpotCode);
    }
}
