package com.stackroute.operatorservice.service;

import com.stackroute.operatorservice.exception.NoSuchCenterExistsException;
import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.repository.AadharCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AadharCenterServiceImp implements AadharCenterService{
    @Autowired
    private AadharCenterRepo ACRepo;

    public AadharCenterServiceImp(){

    }
    public AadharCenterServiceImp(AadharCenterRepo aadharCenterRepo ){
        this.ACRepo=aadharCenterRepo;
    }

    @Override
    public AadharCenterRegister getAadharCenter(String id) {
        return ACRepo.findById(id).orElseThrow(
                ()->new NoSuchElementException("No Such Center present with id")
        );
    }

    @Override
    public List<AadharCenterRegister> findByCity(String city) {
        List<AadharCenterRegister> acr = ACRepo.findByCity(city);
        if(acr.isEmpty()){
            throw new NoSuchCenterExistsException("No Such center Exists within city");
        }
        else {
            return ACRepo.findByCity(city);
        }
    }

    @Override
    public AadharCenterRegister create(AadharCenterRegister aadharcenter, MultipartFile file) throws IOException {
        aadharcenter.setVisualsOfCenter(file.getBytes());
        return ACRepo.save(aadharcenter);
    }

    @Override
    public AadharCenterRegister update(String id, AadharCenterRegister aadharcenter, MultipartFile file) throws IOException {
        AadharCenterRegister acr = ACRepo.findById(id).orElse(null);
        if(acr==null)
            throw new NoSuchCenterExistsException("No such center exits with this id");
        else {
            aadharcenter.setVisualsOfCenter(file.getBytes());
            return ACRepo.save(aadharcenter);
        }

    }

    @Override
    public String deleteById(String id) {
        AadharCenterRegister acr = ACRepo.findById(id).orElse(null);
        if(acr==null)
            throw new NoSuchCenterExistsException("No such center exits with this id");
        else {
            ACRepo.deleteById(id);
            return "Deleted Successfully with this CenterId= "+id;
        }
    }

    @Override
    public List<AadharCenterRegister> findAll() {
        List<AadharCenterRegister>acr = ACRepo.findAll();
        if(acr.isEmpty()){
            throw new NoSuchCenterExistsException("No Center Registered");

        }
        else {
            return ACRepo.findAll();
        }
    }

    @Override
    public List<AadharCenterRegister> findBylocationPin(long locationPin) {
        List<AadharCenterRegister> acr = ACRepo.findBylocationPin(locationPin);
        if(acr.isEmpty()){
            throw new NoSuchCenterExistsException("No Such center Exists within pincode");
        }
        else {
            return acr;
        }
    }
}
