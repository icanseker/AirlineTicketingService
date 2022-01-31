package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.passenger.FareClassEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("fare-class-r")
public interface FareClassRepo extends CrudRepository<FareClassEntity, Character> {
    FareClassEntity findByCode(char code);
}
