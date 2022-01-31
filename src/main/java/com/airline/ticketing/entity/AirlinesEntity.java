package com.airline.ticketing.entity;

import com.airline.ticketing.model.Airlines;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Airlines")
@Table(name = "airlines")
public class AirlinesEntity {

    @Id
    @Column(name = "iata", nullable = false)
    private String IATACode;

    @Column(name = "name", nullable = false)
    private String name;

    public AirlinesEntity() {
    }

    public AirlinesEntity(String IATACode, String name) {
        this.IATACode = IATACode;
        this.name = name;
    }

    public AirlinesEntity(Airlines airlines) {
        this.IATACode = airlines.IATACode();
        this.name = airlines.name();
    }

    public AirlinesEntity setIATACode(String IATACode) {
        this.IATACode = IATACode;
        return this;
    }

    public AirlinesEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Airlines globalForm() {
        return new Airlines(this.IATACode, this.name);
    }
}
