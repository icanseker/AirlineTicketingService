package com.airline.ticketing.model.airplane;

import com.airline.ticketing.model.passenger.PassengerSeatMap;

public class AirPlaneModel {

    private final String ICAOCode;
    private final String modelDef;
    private final AirPlaneCategory category;
    private final PassengerSeatMap passengerSeatMap;
    private final int averageCruiseSpeed;

    public AirPlaneModel(String ICAOCode,String modelDef, AirPlaneCategory category, PassengerSeatMap passengerSeatMap, int averageCruiseSpeed) {
        this.ICAOCode = ICAOCode;
        this.modelDef = modelDef;
        this.category = category;
        this.passengerSeatMap = passengerSeatMap;
        this.averageCruiseSpeed = averageCruiseSpeed;
    }

    public String ICAOCode() {
        return ICAOCode;
    }

    public String modelDef() {
        return modelDef;
    }

    public AirPlaneCategory craftCategory() {
        return category;
    }

    public PassengerSeatMap passengerSeatMap() {
        return passengerSeatMap;
    }

    public int averageCruiseSpeed() {
        return averageCruiseSpeed;
    }
}
