package com.airline.ticketing.entity.location;

import com.airline.ticketing.model.location.City;

import javax.persistence.*;

@Entity(name = "City")
@Table(name = "cities")
public class CityEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public CityEntity() {
    }

    public CityEntity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public CityEntity(City city) {
        this.code = city.code();
        this.name = city.name();
    }

    public CityEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public CityEntity setName(String name) {
        this.name = name;
        return this;
    }

    public City globalForm() {
        return new City(this.code, this.name);
    }
}
