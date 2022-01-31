package com.airline.ticketing.entity.repo;

import com.airline.ticketing.entity.AirlinesEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("airlines-r")
public interface AirlinesRepo extends CrudRepository<AirlinesEntity, String> {
    AirlinesEntity findByIATACode(String code);
}
