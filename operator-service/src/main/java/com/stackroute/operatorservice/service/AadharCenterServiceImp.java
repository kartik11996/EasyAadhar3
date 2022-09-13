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
    public AadharCenterRegister getCenterById(String id) {
        try {
            return ACRepo.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new BusinessException("606", "given center id is null, please send some id to be searched" + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new BusinessException("607", "given center id does not exist in Database" + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("609", "Something went wrong in Service layer while fetching center" + e.getMessage());
        }
    }

    @Override
    public List<AadharCenterRegister> getCenterByCity(String city) {
        List<AadharCenterRegister> acr = null;
        try {
            acr = ACRepo.findByCity(city);
        }
        catch (Exception e) {
            throw new BusinessException("610","Something went wrong in Service layer while fetching all employees" + e.getMessage());
        }
        if(acr.isEmpty())
            throw new BusinessException("611", "No center exits within the city");
        return acr;
    }

    @Override
    public AadharCenterRegister create(AadharCenterRegister aadharcenter, MultipartFile file) {
        if(aadharcenter.getCenterName().isEmpty() || aadharcenter.getCenterName().length() ==0)
            throw new BusinessException("601", "Please send proper center name, center Name is empty");
        try{
            aadharcenter.setVisualsOfCenter(file.getBytes());
            AadharCenterRegister savedCenter = ACRepo.save(aadharcenter);
            return savedCenter;
        }
        catch (IllegalArgumentException e){
            throw new BusinessException("602","given aadhar center details is empty"+ e.getMessage());
        }
        catch (Exception e){
            throw new BusinessException("603","Something went wrong in Service layer while saving the details of center"+e.getMessage());
        }



    }

    @Override
    public AadharCenterRegister update(String id, AadharCenterRegister aadharcenter, MultipartFile file){
        AadharCenterRegister acr = ACRepo.findById(id).get();
        if(acr==null)
            throw new BusinessException("617","No center Exits with this id, Please choose another center ID");
        if(aadharcenter.getCenterName().isEmpty() || aadharcenter.getCenterName().length() ==0)
            throw new BusinessException("618", "Please send proper center name, center Name is empty");
        try{
            aadharcenter.setVisualsOfCenter(file.getBytes());
            return ACRepo.save(aadharcenter);
        }
        catch (IllegalArgumentException e){
            throw new BusinessException("619","given center details is null" + e.getMessage());
        }
        catch (Exception e){
            throw new BusinessException("620","Something went wrong in Service layer while updating the center details" + e.getMessage());
        }
    }

    @Override
    public void deleteById(String id) {
        try{
            ACRepo.deleteById(id);
        }catch(IllegalArgumentException e) {
            throw new BusinessException("612", "given center id is null, please send some id to be deleted" + e.getMessage());
        }
        catch (Exception e){
            throw new BusinessException("613","Something went wrong in Service layer while fetching id for deleting" + e.getMessage());
        }
    }

    @Override
    public List<AadharCenterRegister> getAllCenter() {
        List<AadharCenterRegister>acr = null;
        try{
            acr=ACRepo.findAll();
        }
        catch(Exception e){
            throw new BusinessException("605","Something went Wrong in Service layer while fetching all centers"+e.getMessage());
        }
        if(acr.isEmpty())
           throw new BusinessException("604","No Center Registered, we have nothing to return");
        return acr;
    }

    @Override
    public List<AadharCenterRegister> getCenterByLocationPin(long locationPin) {
        try {
            return ACRepo.findBylocationPin(locationPin);

        }catch (IllegalArgumentException e) {
            throw new BusinessException("614","given location pin is null, please send some location pin to be searched" + e.getMessage());
        }
        catch (NoSuchElementException e) {
            throw new BusinessException("615","given location Pin does not exist in DataBase" + e.getMessage());
        }catch (Exception e) {
            throw new BusinessException("616","Something went wrong in Service layer while fetching all employees" + e.getMessage());
        }

    }
}
