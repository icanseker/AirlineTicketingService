package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.location.CountryEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("country-r")
public interface CountryRepo extends CrudRepository<CountryEntity, String> {
    CountryEntity findByAlpha3Code(String alpha3Code);
}
