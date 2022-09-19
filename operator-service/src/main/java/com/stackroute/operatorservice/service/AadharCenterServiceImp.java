package com.stackroute.operatorservice.service;

import com.stackroute.operatorservice.exception.BusinessException;
import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.repository.AadharCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AadharCenterServiceImp implements AadharCenterService{
    private AadharCenterRepo ACRepo;

    public AadharCenterServiceImp(){

    }
    @Autowired
    public AadharCenterServiceImp(AadharCenterRepo aadharCenterRepo ){
        this.ACRepo=aadharCenterRepo;
    }


    @Override
    public AadharCenterRegister create(AadharCenterRegister aadharcenter, MultipartFile file) throws IOException {
        if(aadharcenter.getCenterName().isEmpty() || aadharcenter.getCenterName().length() ==0)
            throw new BusinessException("601", "Please send proper center name, center Name is empty");

            aadharcenter.setVisualsOfCenter(file.getBytes());
            AadharCenterRegister savedCenter = ACRepo.save(aadharcenter);
            return savedCenter;
    }

    @Override
    public AadharCenterRegister update(String id, AadharCenterRegister aadharcenter, MultipartFile file) throws BusinessException, IOException {
        AadharCenterRegister acr = ACRepo.findById(id).get();
        Optional<AadharCenterRegister>optional=ACRepo.findById(id);

        if(aadharcenter.getCenterName().isEmpty() || aadharcenter.getCenterName().length() ==0)
            throw new BusinessException("602", "Please send proper center name, center Name is empty");

        if(optional.isPresent()) {
            // acr.setCenterId(aadharcenter.getCenterId());
            acr.setVisualsOfCenter(file.getBytes());
            acr.setAddress(aadharcenter.getAddress());
            acr.setCity(aadharcenter.getCity());
            acr.setCenterDescription(aadharcenter.getCenterDescription());
            acr.setAmenities(aadharcenter.getAmenities());
            acr.setCenterName(aadharcenter.getCenterName());
            acr.setClosingTime(aadharcenter.getClosingTime());
            acr.setOpeningTime(aadharcenter.getOpeningTime());
            acr.setContactInfo(aadharcenter.getContactInfo());
            acr.setPostedDate(aadharcenter.getPostedDate());
            acr.setLocationPin(aadharcenter.getLocationPin());
            acr.setPlacesNearBy(aadharcenter.getPlacesNearBy());
            acr.setState(aadharcenter.getState());
            acr.setTransportFacilities(aadharcenter.getTransportFacilities());
        }
        else {
            throw new BusinessException("603","No center Exits with this id, Please choose another center ID");


        }

            return ACRepo.save(acr);

    }

    @Override
    public boolean deleteById(String id) {
        Optional<AadharCenterRegister> acr = ACRepo.findById(id);
        if(acr.isEmpty())
            throw new BusinessException("604","No center Exits with this id, Please choose another center");
        else{
            ACRepo.deleteById(id);
            return true;
        }
    }

    @Override
    public List<AadharCenterRegister> getAllCenter() {
        List<AadharCenterRegister>acr = null;
            acr=ACRepo.findAll();
        if(acr.isEmpty())
           throw new BusinessException("605","No Center Registered, we have nothing to return");
        else
            return acr;
    }

    @Override
    public List<AadharCenterRegister> getCenterByLocationPin(long locationPin) {
        List<AadharCenterRegister>acr = null;
        acr=ACRepo.findBylocationPin(locationPin);
        if(acr.isEmpty()){
            throw new BusinessException("606","No center associated with this locationpin, Please try other pin");
        }
        return acr;
    }
    @Override
    public AadharCenterRegister getCenterById(String id) {
        Optional<AadharCenterRegister>acr = ACRepo.findById(id);
        if(acr.isPresent()){
            return acr.get();
        }
        else{
            throw new BusinessException("607", "given center id does not exist in Database.");
        }

    }

    @Override
    public List<AadharCenterRegister> getCenterByCity(String city) {
        List<AadharCenterRegister> acr = null;
        acr = ACRepo.findByCity(city);
        if(acr.isEmpty())
            throw new BusinessException("608", "No center exits within the city");
        return acr;
    }

}
