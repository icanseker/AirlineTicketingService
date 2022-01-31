package com.airline.ticketing.api.response;

import java.util.Hashtable;

public class BasicResponse {

    public String message;
    public final Hashtable<String, String> properties;

    public BasicResponse() {
        this.message = "";
        this.properties = new Hashtable<>();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addProperty(String key, String value) {
        properties.put(key, value);
    }
}
