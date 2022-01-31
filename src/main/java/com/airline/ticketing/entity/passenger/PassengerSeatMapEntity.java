package com.airline.ticketing.entity.passenger;

import com.airline.ticketing.model.passenger.PassengerSeatMap;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity(name = "PassengerSeatMap")
@Table(name = "passenger_seat_maps")
public class PassengerSeatMapEntity {

    @Id
    @Column(name = "map_code")
    private String mapCode;

    @Column(name = "description")
    private String description;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "seat_type_map", joinColumns = @JoinColumn(name = "seat_map_code", referencedColumnName = "map_code"))
    @MapKeyColumn(name = "seat_spot_code")
    @Column(name = "seat_type_code", nullable = false)
    private Map<String, String> seatTypeCodeMap; // map by seat spot code, mapping seat type code

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "fare_class_map", joinColumns = @JoinColumn(name = "seat_map_code", referencedColumnName = "map_code"))
    @MapKeyColumn(name = "seat_spot_code")
    @Column(name = "fare_class_code", nullable = false)
    private Map<String, Character> fareClassCodeMap; // map by seat spot code, mapping fare class code

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "reserved_seat_map", joinColumns = @JoinColumn(name = "seat_map_code", referencedColumnName = "map_code"))
    @Column(name = "seat_spot_code", nullable = false)
    private Set<String> reservedSeats;

    public PassengerSeatMapEntity() {
    }

    public PassengerSeatMapEntity(PassengerSeatMap seatMap) {
        this.mapCode = seatMap.mapCode();
        this.description = seatMap.description();
        this.seatTypeCodeMap = seatMap.seatTypeCodeMap();
        this.fareClassCodeMap = seatMap.fareClassCodeMap();
        this.reservedSeats = seatMap.reservedSeats();
    }

    public PassengerSeatMapEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public PassengerSeatMap globalForm() {
        return new PassengerSeatMap(this.mapCode, this.description, this.seatTypeCodeMap, this.fareClassCodeMap, this.reservedSeats);
    }
}
