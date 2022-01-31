package com.airline.ticketing.entity.passenger;

import com.airline.ticketing.model.passenger.AirFareClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "FareClass")
@Table(name = "fare_classes")
public class FareClassEntity {

    @Id
    @Column(name = "code", nullable = false)
    private Character code;

    @Column(name = "correspond", nullable = false)
    private String correspond;

    @Column(name = "price_coefficient", nullable = false)
    private double priceCoefficient = 1.0;

    public FareClassEntity() {
    }

    public FareClassEntity(char code, String correspond) {
        this.code = code;
        this.correspond = correspond;
    }

    public FareClassEntity(char code, String correspond, double priceCoefficient) {
        this.code = code;
        this.correspond = correspond;
        this.priceCoefficient = priceCoefficient;
    }

    public FareClassEntity(AirFareClass fareClass) {
        this.code = fareClass.code();
        this.correspond = fareClass.correspond();
        this.priceCoefficient = fareClass.priceCoefficient();
    }

    public FareClassEntity setCode(char code) {
        this.code = code;
        return this;
    }

    public FareClassEntity setCorrespond(String correspond) {
        this.correspond = correspond;
        return this;
    }

    public FareClassEntity setPriceCoefficient(double priceCoefficient) {
        this.priceCoefficient = priceCoefficient;
        return this;
    }

    public double getPriceCoefficient() {
        return priceCoefficient;
    }

    public AirFareClass globalForm() {
        return new AirFareClass(this.code, this.correspond, this.priceCoefficient);
    }
}
