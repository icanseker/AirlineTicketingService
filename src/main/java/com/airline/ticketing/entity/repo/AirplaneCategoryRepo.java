package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.airplane.AirplaneCategoryEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("airplane-cat-r")
public interface AirplaneCategoryRepo extends CrudRepository<AirplaneCategoryEntity, String> {
    AirplaneCategoryEntity findByCode(String code);
}
