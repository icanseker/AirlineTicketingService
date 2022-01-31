package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.passenger.SeatTypeEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("seat-type-r")
public interface SeatTypeRepo extends CrudRepository<SeatTypeEntity, String> {
    SeatTypeEntity findByCode(String code);
}
