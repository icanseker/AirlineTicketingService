package com.airline.ticketing.entity.location;

import com.airline.ticketing.model.location.Timezone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Timezone")
@Table(name = "timezones")
public class TimezoneEntity {

    @Id
    @Column(name = "zone_id")
    private String zoneId;

    public TimezoneEntity() {
    }

    public TimezoneEntity(String zoneId) {
        this.zoneId = zoneId;
    }

    public TimezoneEntity(Timezone timeZone) {
        this.zoneId = timeZone.id();
    }

    public TimezoneEntity setZoneId(String zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public Timezone globalForm() {
        return new Timezone(this.zoneId);
    }
}
