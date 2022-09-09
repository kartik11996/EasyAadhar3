package com.stackroute.operatorservice.service;

import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.repository.AadharCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<AadharCenterRegister> getAadharCenter(String id) {
        return ACRepo.findById(id);
    }

    @Override
    public List<AadharCenterRegister> findByCity(String city) {
        return ACRepo.findByCity(city);
    }

    @Override
    public AadharCenterRegister create(AadharCenterRegister aadharcenter) {
        return ACRepo.save(aadharcenter);
    }

    @Override
    public AadharCenterRegister update(String id, AadharCenterRegister aadharcenter) {
        return ACRepo.save(aadharcenter);
    }

    @Override
    public void deleteById(String id) {
        ACRepo.deleteById(id);
    }

    @Override
    public List<AadharCenterRegister> findAll() {
        return ACRepo.findAll();
    }
}
