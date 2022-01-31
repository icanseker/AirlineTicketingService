package com.airline.ticketing.entity.location;

import com.airline.ticketing.model.location.Country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Country")
@Table(name = "countries")
public class CountryEntity {

    @Id
    @Column(name = "a3code", nullable = false, unique = true)
    private String alpha3Code;

    @Column(name = "name", nullable = false)
    private String stateName;

    public CountryEntity() {
    }

    public CountryEntity(String alpha3Code, String stateName) {
        this.alpha3Code = alpha3Code;
        this.stateName = stateName;
    }

    public CountryEntity(Country country) {
        this(country.alpha3Code(), country.stateName());
    }

    public CountryEntity setAlpha3Code(String alpha2Code) {
        this.alpha3Code = alpha2Code;
        return this;
    }

    public CountryEntity setStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public Country globalForm() {
        return new Country(this.alpha3Code, this.stateName);
    }
}
