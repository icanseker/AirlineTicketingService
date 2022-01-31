package com.airline.ticketing.entity.ticket;

import com.airline.ticketing.model.ticket.PassengerDetail;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "PassengerDetail")
@Table(name = "passenger_details")
public class PassengerDetailEntity {

    @Id
    @GeneratedValue
    @Column(name = "detail_uid", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    public PassengerDetailEntity() {
    }

    public PassengerDetailEntity(String fullName) {
        this.fullName = fullName;
    }

    public PassengerDetailEntity(PassengerDetail detail) {
        this.fullName = detail.fullName();
    }

    public PassengerDetail globalForm() {
        return new PassengerDetail(this.fullName);
    }
}
