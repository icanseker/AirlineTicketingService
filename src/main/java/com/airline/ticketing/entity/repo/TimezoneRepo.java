package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.location.TimezoneEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("timezone-r")
public interface TimezoneRepo extends CrudRepository<TimezoneEntity, String> {
    TimezoneEntity findByZoneId(String zoneId);
}
