package com.airline.ticketing.entity.airplane;

import com.airline.ticketing.entity.passenger.PassengerSeatMapEntity;
import com.airline.ticketing.model.airplane.AirPlaneModel;

import javax.persistence.*;

@Entity(name = "AirPlaneModel")
@Table(name = "airplane_models")
public class AirplaneModelEntity {

    @Id
    @Column(name = "icao", nullable = false)
    private String ICAOCode;

    @Column(name = "definition", nullable = false, unique = true)
    private String modelDef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "code")
    private AirplaneCategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_map_code", nullable = false, referencedColumnName = "map_code")
    private PassengerSeatMapEntity seatMap;

    @Column(name = "avg_cr_speed", nullable = false)
    private int averageCruiseSpeed;

    public AirplaneModelEntity() {
    }

    public AirplaneModelEntity(String ICAOCode, String modelDef, AirplaneCategoryEntity category, PassengerSeatMapEntity passengerSeatMapEntity, int averageCruiseSpeed) {
        this.ICAOCode = ICAOCode;
        this.modelDef = modelDef;
        this.category = category;
        this.seatMap = passengerSeatMapEntity;
        this.averageCruiseSpeed = averageCruiseSpeed;
    }

    public AirplaneModelEntity(AirPlaneModel model) {
        this.ICAOCode = model.ICAOCode();
        this.modelDef = model.modelDef();
        this.category = new AirplaneCategoryEntity(model.craftCategory());
        this.averageCruiseSpeed = model.averageCruiseSpeed();
    }

    public AirplaneModelEntity setICAOCode(String ICAOCode) {
        this.ICAOCode = ICAOCode;
        return this;
    }

    public AirplaneModelEntity setModelDef(String modelDef) {
        this.modelDef = modelDef;
        return this;
    }

    public AirplaneModelEntity setCategory(AirplaneCategoryEntity category) {
        this.category = category;
        return this;
    }

    public AirplaneModelEntity setAverageCruiseSpeed(int averageCruiseSpeed) {
        this.averageCruiseSpeed = averageCruiseSpeed;
        return this;
    }

    public AirPlaneModel globalForm() {
        return new AirPlaneModel(this.ICAOCode, this.modelDef, this.category.globalForm(), seatMap.globalForm(), this.averageCruiseSpeed);
    }
}
