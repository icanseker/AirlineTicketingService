package com.airline.ticketing.entity;

import com.airline.ticketing.entity.airplane.AirplaneModelEntity;
import com.airline.ticketing.entity.passenger.PassengerSeatMapEntity;
import com.airline.ticketing.model.Airplane;

import javax.persistence.*;

@Entity(name = "Airplane")
@Table(name = "airplanes")
public class AirplaneEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_icao", nullable = false, referencedColumnName = "icao")
    private AirplaneModelEntity model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airlines_iata", nullable = false, referencedColumnName = "iata")
    private AirlinesEntity airlines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_seat_map_code", referencedColumnName = "map_code")
    private PassengerSeatMapEntity passengerSeatMap;

    public AirplaneEntity() {
    }

    public AirplaneEntity(String code, String name, AirplaneModelEntity model, AirlinesEntity airlines, PassengerSeatMapEntity passengerSeatMap) {
        this.code = code;
        this.name = name;
        this.model = model;
        this.airlines = airlines;
        this.passengerSeatMap = passengerSeatMap;
    }

    public AirplaneEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AirplaneEntity setModel(AirplaneModelEntity model) {
        this.model = model;
        return this;
    }

    public AirplaneEntity setAirlines(AirlinesEntity airlines) {
        this.airlines = airlines;
        return this;
    }

    public AirplaneEntity setPassengerSeatMap(PassengerSeatMapEntity passengerSeatMap) {
        this.passengerSeatMap = passengerSeatMap;
        return this;
    }

    public Airplane globalForm() {
        return new Airplane(
                code,
                this.name,
                this.model.globalForm(),
                this.airlines.globalForm(),
                this.passengerSeatMap == null ? this.model.globalForm().passengerSeatMap() : this.passengerSeatMap.globalForm()
        );
    }
}
