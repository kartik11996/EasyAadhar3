package com.stackroute.operatorservice.service;

import com.stackroute.operatorservice.model.AadharCenterRegister;

import java.util.List;
import java.util.Optional;

public interface AadharCenterService {
    Optional<AadharCenterRegister> getAadharCenter(String id);
    List<AadharCenterRegister> findByCity(String city);
    AadharCenterRegister create(AadharCenterRegister aadharcenter);
    AadharCenterRegister update(String id, AadharCenterRegister aadharcenter);
    void deleteById(String id);
    List<AadharCenterRegister> findAll();




    //List<AadharCenterRegister> findByState(String state);
  //  List<AadharCenterRegister> findByPincode(long locationPin);







}
