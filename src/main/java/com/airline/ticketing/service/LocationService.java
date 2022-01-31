package com.airline.ticketing.service;

import com.airline.ticketing.entity.AirportEntity;
import com.airline.ticketing.entity.PortLocationEntity;
import com.airline.ticketing.entity.location.TimezoneEntity;
import com.airline.ticketing.entity.location.CityEntity;
import com.airline.ticketing.entity.location.CountryEntity;
import com.airline.ticketing.entity.repo.AirportRepo;
import com.airline.ticketing.entity.repo.CityRepo;
import com.airline.ticketing.entity.repo.CountryRepo;
import com.airline.ticketing.entity.repo.TimezoneRepo;
import com.airline.ticketing.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;
    private final TimezoneRepo timezoneRepo;
    private final AirportRepo airportRepo;

    @Autowired
    public LocationService(
            @Qualifier("city-r") CityRepo cityRepo,
            @Qualifier("country-r") CountryRepo countryRepo,
            @Qualifier("timezone-r") TimezoneRepo timezoneRepo,
            @Qualifier("airport-r") AirportRepo airportRepo
    ) {
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
        this.timezoneRepo = timezoneRepo;
        this.airportRepo = airportRepo;
    }

    public void addCity(String code, String name) throws Exception {

        CityEntity entity = this.cityRepo.findByCode(code);
        if (entity != null) {
            throw new Exception("City is already exist with given code: " + code + " [with name of '" + entity.globalForm().name() + "']");
        }
        this.cityRepo.save(new CityEntity(code, name));
    }

    public void addCountry(String alpha3Code, String stateName) throws Exception {

        CountryEntity entity = this.countryRepo.findByAlpha3Code(alpha3Code);
        if (entity != null) {
            throw new Exception("Country Already Exist with given code: " + alpha3Code + " [with name of '" + entity.globalForm().stateName() + "']");
        }
        this.countryRepo.save(new CountryEntity(alpha3Code, stateName));
    }

    public void defineTimezone(String zoneId) throws Exception {

        TimezoneEntity entity = this.timezoneRepo.findByZoneId(zoneId);
        if (entity != null) {
            throw new Exception("Timezone Already Exist with given id: " + zoneId);
        }
        this.timezoneRepo.save(new TimezoneEntity(zoneId));
    }

    public Airport pinAirport(String IATACode, String name, String countryCode, String cityCode, String longitude, String latitude, String timeZoneId) throws Exception {

        AirportEntity entity = this.airportRepo.findByIATACode(IATACode);
        if (entity != null) {
            throw new Exception("Airport Already Exist with given IATA Code: " + IATACode + " [with name of '" + entity.globalForm().name() + "']");
        }
        return this.airportRepo.save(
                new AirportEntity(
                        IATACode,
                        name,
                        new PortLocationEntity(
                                this.countryRepo.findByAlpha3Code(countryCode),
                                this.cityRepo.findByCode(cityCode),
                                longitude,
                                latitude,
                                this.timezoneRepo.findByZoneId(timeZoneId)
                        )
                )
        ).globalForm();
    }

    public List<Airport> getAirports() {

        List<Airport> airports = new ArrayList<>();
        this.airportRepo.findAll().forEach(airportEntity -> airports.add(airportEntity.globalForm()));

        return airports;
    }

    public List<Airport> getAirports(String countryCode) {

        List<Airport> airports = new ArrayList<>();
        this.airportRepo.findAllByLocation_Country_Alpha3Code(countryCode).forEach(airportEntity -> airports.add(airportEntity.globalForm()));

        return airports;
    }

    public Airport getAirport(String iataCode) {
        return this.airportRepo.findByIATACode(iataCode).globalForm();
    }
}
