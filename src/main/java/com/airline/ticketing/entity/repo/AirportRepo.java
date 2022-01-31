package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.AirportEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("airport-r")
public interface AirportRepo extends CrudRepository<AirportEntity, String> {
    AirportEntity findByIATACode(String code);
    List<AirportEntity> findAllByLocation_Country_Alpha3Code(String countryCode);
}
