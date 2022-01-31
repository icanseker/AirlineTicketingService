package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.AirplaneEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("airplane-r")
public interface AirplaneRepo extends CrudRepository<AirplaneEntity, String> {
    AirplaneEntity findByCode(String code);
}
