package com.stackroute.operatorservice.repository;

import com.stackroute.operatorservice.model.AadharCenterRegister;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AadharCenterRepo extends ElasticsearchRepository<AadharCenterRegister, String> {
    //List<AadharCenterRegister>findByState(String state);
    List<AadharCenterRegister>findByCity(String city);
    List<AadharCenterRegister> findAll();


    List<AadharCenterRegister> findBylocationPin(long locationPin);
}
