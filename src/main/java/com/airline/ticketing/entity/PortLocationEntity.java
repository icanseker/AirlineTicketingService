package com.airline.ticketing.entity;

import com.airline.ticketing.entity.location.CityEntity;
import com.airline.ticketing.entity.location.CountryEntity;
import com.airline.ticketing.entity.location.TimezoneEntity;
import com.airline.ticketing.model.PortLocation;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "AirPortLocation")
@Table(name = "airport_locations")
public class PortLocationEntity {

    @Id
    @GeneratedValue
    @Column(name = "location_id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code", nullable = false, referencedColumnName = "a3code")
    private CountryEntity country;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_code", nullable = false, referencedColumnName = "code")
    private CityEntity city;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_zone", nullable = false, referencedColumnName = "zone_id")
    private TimezoneEntity timeZone;

    public PortLocationEntity() {
    }

    public PortLocationEntity(CountryEntity country, CityEntity city, String longitude, String latitude, TimezoneEntity timeZone) {
        this.country = country;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeZone = timeZone;
    }

    public PortLocationEntity(PortLocation location) {
        this.country = new CountryEntity(location.country());
        this.city = new CityEntity(location.city());
        this.longitude = location.longitude();
        this.latitude = location.latitude();
        this.timeZone = new TimezoneEntity(location.timeZone());
    }

    public PortLocationEntity setCountry(CountryEntity country) {
        this.country = country;
        return this;
    }

    public PortLocationEntity setCity(CityEntity city) {
        this.city = city;
        return this;
    }

    public PortLocationEntity setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public PortLocationEntity setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public PortLocationEntity setTimeZone(TimezoneEntity timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public PortLocation globalForm() {
        return new PortLocation(this.country.globalForm(), this.city.globalForm(), this.longitude, this.latitude, this.timeZone.globalForm());
    }
}
