package com.airline.ticketing.entity;

import com.airline.ticketing.model.Flight;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "Flight")
@Table(name = "flights")
public class FlightEntity {

    @Id
    @Column(name = "code", updatable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_code", nullable = false)
    private AirRouteEntity route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_code", nullable = false)
    private AirplaneEntity airplane;

    @Column(name = "date_time", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dateTime;

    @Column(name = "raw_ticket_price", nullable = false)
    private double rawTicketPrice; // in dollars

    public FlightEntity() {
    }

    public FlightEntity(AirRouteEntity route, AirplaneEntity airplane, ZonedDateTime dateTime, double rawTicketPrice) {
        this.route = route;
        this.airplane = airplane;
        this.dateTime = dateTime;
        this.rawTicketPrice = rawTicketPrice;
        this.code = route.globalForm().code() + "-" + dateTime.format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmZ"));
    }

    public FlightEntity setRoute(AirRouteEntity route) {
        this.route = route;
        return this;
    }

    public FlightEntity setAirplane(AirplaneEntity airplane) {
        this.airplane = airplane;
        return this;
    }

    public FlightEntity setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public FlightEntity setRawTicketPrice(double rawTicketPrice) {
        this.rawTicketPrice = rawTicketPrice;
        return this;
    }

    public AirplaneEntity airplane() {
        return airplane;
    }

    public double rawTicketPrice() {
        return rawTicketPrice;
    }

    public Flight globalForm() {
        return new Flight(this.code, this.route.globalForm(), this.airplane.globalForm(), this.dateTime);
    }
}
