package com.airline.ticketing.entity.passenger;

import com.airline.ticketing.model.passenger.PassengerSeatType;

import javax.persistence.*;

@Entity(name = "PassengerSeatType")
@Table(name = "seat_types")
public class SeatTypeEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "definition", nullable = false)
    private String definition;

    public SeatTypeEntity() {
    }

    public SeatTypeEntity(String code, String definition) {
        this.code = code;
        this.definition = definition;
    }

    public SeatTypeEntity(PassengerSeatType seatType) {
        this.code = seatType.code();
        this.definition = seatType.definition();
    }

    public SeatTypeEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public SeatTypeEntity setDefinition(String definition) {
        this.definition = definition;
        return this;
    }

    public PassengerSeatType globalForm() { // model definition
        return new PassengerSeatType(code, this.definition);
    }
}
