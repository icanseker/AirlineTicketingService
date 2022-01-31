package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.location.CityEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("city-r")
public interface CityRepo extends CrudRepository<CityEntity, String> {
    CityEntity findByCode(String code);
}
