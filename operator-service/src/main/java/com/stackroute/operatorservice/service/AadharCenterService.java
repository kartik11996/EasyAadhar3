package com.stackroute.operatorservice.service;

import com.stackroute.operatorservice.exception.BusinessException;
import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.model.Appointment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AadharCenterService {
    AadharCenterRegister getCenterById(String id);
    List<AadharCenterRegister> getCenterByCity(String city);
    AadharCenterRegister create(AadharCenterRegister aadharcenter, MultipartFile file) throws BusinessException, IOException;
    AadharCenterRegister update(String id, AadharCenterRegister aadharcenter, MultipartFile file) throws BusinessException, IOException;
    boolean deleteById(String id);
    List<AadharCenterRegister> getAllCenter();
    List<AadharCenterRegister> getCenterByLocationPin(long locationPin);
    Appointment createAppointment(String id, Appointment appointment);





    //List<AadharCenterRegister> findByState(String state);
  //  List<AadharCenterRegister> findByPincode(long locationPin);







}
