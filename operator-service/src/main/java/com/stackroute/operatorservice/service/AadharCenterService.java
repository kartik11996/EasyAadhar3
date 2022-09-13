package com.stackroute.operatorservice.service;

import com.stackroute.operatorservice.model.AadharCenterRegister;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AadharCenterService {
    AadharCenterRegister getAadharCenter(String id);
    List<AadharCenterRegister> findByCity(String city);
    AadharCenterRegister create(AadharCenterRegister aadharcenter, MultipartFile file) throws IOException;
    AadharCenterRegister update(String id, AadharCenterRegister aadharcenter, MultipartFile file) throws IOException;
    String deleteById(String id);
    List<AadharCenterRegister> findAll();
    List<AadharCenterRegister> findBylocationPin(long locationPin);





    //List<AadharCenterRegister> findByState(String state);
  //  List<AadharCenterRegister> findByPincode(long locationPin);







}
