package com.airline.ticketing.entity;

import com.airline.ticketing.model.AirRoute;

import javax.persistence.*;

@Entity(name = "AirRoute")
@Table(name = "air_routes")
public class AirRouteEntity {

    @Id
    @Column(name = "code", nullable = false)
    private String routeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_iata", nullable = false, referencedColumnName = "iata")
    private AirportEntity departurePort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landing_iata", nullable = false, referencedColumnName = "iata")
    private AirportEntity landingPort;

    @Column(name = "distance_km", nullable = false)
    private int distance;

    public AirRouteEntity() {
    }

    public AirRouteEntity(String routeCode, AirportEntity departurePort, AirportEntity landingPort, int distance) {
        this.routeCode = routeCode;
        this.departurePort = departurePort;
        this.landingPort = landingPort;
        this.distance = distance;
    }

    public AirRouteEntity(AirRoute airRoute) {
        this.routeCode = airRoute.code();
        this.departurePort = new AirportEntity(airRoute.departurePort());
        this.landingPort = new AirportEntity(airRoute.landingPort());
        this.distance = airRoute.distanceKilometers();
    }

    public AirRouteEntity setRouteCode(String routeCode) {
        this.routeCode = routeCode;
        return this;
    }

    public AirRouteEntity setDeparturePort(AirportEntity departurePort) {
        this.departurePort = departurePort;
        return this;
    }

    public AirRouteEntity setLandingPort(AirportEntity landingPort) {
        this.landingPort = landingPort;
        return this;
    }

    public AirRouteEntity setDistance(int distance) {
        this.distance = distance;
        return this;
    }

    public AirRoute globalForm() {
        return new AirRoute(this.routeCode, this.departurePort.globalForm(), this.landingPort.globalForm(), this.distance);
    }
}
