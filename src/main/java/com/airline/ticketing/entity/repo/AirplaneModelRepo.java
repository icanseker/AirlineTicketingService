package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.airplane.AirplaneModelEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("airplane-model-r")
public interface AirplaneModelRepo extends CrudRepository<AirplaneModelEntity, String> {
    AirplaneModelEntity findByICAOCode(String code);
}
