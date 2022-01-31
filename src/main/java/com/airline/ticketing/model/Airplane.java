package com.airline.ticketing.model;

import com.airline.ticketing.model.airplane.AirPlaneModel;
import com.airline.ticketing.model.passenger.PassengerSeatMap;

public final class Airplane {

    private final String code;
    private final String name;
    private final AirPlaneModel model;
    private final Airlines airlines;
    private final PassengerSeatMap passengerSeatMap;

    public Airplane(String code, String name, AirPlaneModel model, Airlines airlines, PassengerSeatMap passengerSeatMap) {
        this.code = code;
        this.name = name;
        this.model = model;
        this.airlines = airlines;
        this.passengerSeatMap = passengerSeatMap;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public AirPlaneModel model() {
        return model;
    }

    public Airlines airlines() {
        return airlines;
    }

    public PassengerSeatMap passengerSeatMap() {
        return passengerSeatMap;
    }

    @Override
    public String toString() {
        return "Airplane " + code() + ", " + name() + ", " + model().modelDef() + " belong to " + airlines().toString();
    }
}
