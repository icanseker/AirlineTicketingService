package com.airline.ticketing.entity.airplane;

import com.airline.ticketing.model.airplane.AirPlaneCategory;

import javax.persistence.*;

@Entity(name = "AirPlaneCategory")
@Table(name = "airplane_categories")
public class AirplaneCategoryEntity {

    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    public AirplaneCategoryEntity() {
    }

    public AirplaneCategoryEntity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public AirplaneCategoryEntity(AirPlaneCategory category) {
        this.code = category.getCode();
        this.name = category.getName();
    }

    public AirplaneCategoryEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public AirplaneCategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AirPlaneCategory globalForm() {
        return new AirPlaneCategory(this.code, this.name);
    }
}
