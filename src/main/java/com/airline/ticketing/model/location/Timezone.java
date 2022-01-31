package com.airline.ticketing.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Timezone {

    private final String zoneId;

    public Timezone(@JsonProperty(value = "id", required = true) String zoneId) {
        this.zoneId = zoneId;
    }

    public String id() {
        return zoneId;
    }
}
