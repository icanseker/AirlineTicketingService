package com.airline.ticketing.service;

import com.airline.ticketing.entity.AirlinesEntity;
import com.airline.ticketing.entity.repo.AirlinesRepo;
import com.airline.ticketing.model.Airlines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlinesService {

    private final AirlinesRepo airlinesRepo;

    @Autowired
    public AirlinesService(@Qualifier("airlines-r") AirlinesRepo airlinesRepo) {
        this.airlinesRepo = airlinesRepo;
    }

    public void add(String iataCode, String name) throws Exception {

        AirlinesEntity entity = this.airlinesRepo.findByIATACode(iataCode);
        if (entity != null) {
            throw new Exception("Airlines company is already exist with given iata: " + iataCode + " [with name of '" + entity.globalForm().name() + "']");
        }
        this.airlinesRepo.save(new AirlinesEntity(iataCode, name)).globalForm();
    }

    public List<Airlines> all() {

        List<Airlines> airlines = new ArrayList<>();
        this.airlinesRepo.findAll().forEach(airlinesEntity -> airlines.add(airlinesEntity.globalForm()));

        return airlines;
    }

    public Airlines find(String iataCode) {
        return this.airlinesRepo.findByIATACode(iataCode).globalForm();
    }
}
