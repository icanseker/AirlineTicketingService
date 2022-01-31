package com.airline.ticketing.model.passenger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PassengerSeatMap {

    private final String mapCode;
    private final String description;

    private final Map<String, String> seatTypeCodeMap; // map by seat spot code, mapping seat type code
    private final Map<String, Character> fareClassCodeMap; // map by seat spot code, mapping fare class code

    private final Set<String> reservedSeats;

    public PassengerSeatMap(String mapCode, String description) {
        this.mapCode = mapCode;
        this.description = description;
        this.seatTypeCodeMap = new HashMap<>();
        this.fareClassCodeMap = new HashMap<>();
        this.reservedSeats = new HashSet<>();
    }

    public PassengerSeatMap(String mapCode, String description, Map<String, String> seatTypeCodeMap, Map<String, Character> fareClassCodeMap, Set<String> reservedSeats) {
        this.mapCode = mapCode;
        this.description = description;
        this.seatTypeCodeMap = seatTypeCodeMap;
        this.fareClassCodeMap = fareClassCodeMap;
        this.reservedSeats = reservedSeats;
    }

    public void addSeat(PassengerSeat seat) {
        String seatSpotCode = seat.getSpotCode();
        seatTypeCodeMap.put(seatSpotCode, seat.seatType().code());
        fareClassCodeMap.put(seatSpotCode, seat.fareClass().code());
    }

    public void addSeat(String spotCode, String typeCode, char fareClassCode) {
        seatTypeCodeMap.put(spotCode, typeCode);
        fareClassCodeMap.put(spotCode, fareClassCode);
    }

    public int passengerSeatCapacity(char fareClassCode) {
        return (int) fareClassCodeMap.entrySet().stream().filter(fareCode -> fareCode.getValue().equals(fareClassCode)).count();
    }

    public int passengerSeatCapacity() {
        return seatTypeCodeMap.size();
    }

    public boolean hasASeat(char fareClassCode, String seatTypeCode) {
        return fareClassCodeMap.containsValue(fareClassCode) && seatTypeCodeMap.containsValue(seatTypeCode);
    }

    public double fullnessPercentage() {
        if(reservedSeats.size() == 0) return 0.0;
        return ((double) reservedSeats.size() / (double) passengerSeatCapacity()) * 100.0;
    }

    public String reserveASeat(char fareClassCode, String seatTypeCode) throws Exception {

        if(hasASeat(fareClassCode, seatTypeCode)) {
            for(Map.Entry<String, Character> fareClassEntry: fareClassCodeMap.entrySet()) {

                char seatFearClassCode = fareClassEntry.getValue();
                if(fareClassCode == seatFearClassCode) {
                    String seatSpotCode = fareClassEntry.getKey();
                    if(!reservedSeats.contains(seatSpotCode)) {

                        reservedSeats.add(seatSpotCode);
                        return seatSpotCode;
                    }
                }
            }
            throw new Exception("No empty seat for " + "fare class " + fareClassCode + " and " + "seat type of " + seatTypeCode);
        }

        throw new Exception("No suitable seat for " + "fare class " + fareClassCode + " and " + "seat type of " + seatTypeCode);
    }

    public String mapCode() {
        return mapCode;
    }

    public String description() {
        return description;
    }

    public Map<String, String> seatTypeCodeMap() {
        return seatTypeCodeMap;
    }

    public Map<String, Character> fareClassCodeMap() {
        return fareClassCodeMap;
    }

    public Set<String> reservedSeats() {
        return reservedSeats;
    }
}
