package com.airline.ticketing.entity;

import com.airline.ticketing.model.Airport;

import javax.persistence.*;

@Entity(name = "Airport")
@Table(name = "airports")
public class AirportEntity {

    @Id
    @Column(name = "iata", nullable = false)
    private String IATACode;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", unique = true, nullable = false, referencedColumnName = "location_id")
    private PortLocationEntity location;

    public AirportEntity() {
    }

    public AirportEntity(String IATACode, String name, PortLocationEntity location) {
        this.IATACode = IATACode;
        this.name = name;
        this.location = location;
    }

    public AirportEntity(Airport airport) {
        this.IATACode = airport.IATACode();
        this.name = airport.name();
        this.location = new PortLocationEntity(airport.location());
    }

    public AirportEntity setIATACode(String IATACode) {
        this.IATACode = IATACode;
        return this;
    }

    public AirportEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AirportEntity setLocation(PortLocationEntity location) {
        this.location = location;
        return this;
    }

    public Airport globalForm() {
        return new Airport(this.IATACode, this.name, this.location.globalForm());
    }
}
