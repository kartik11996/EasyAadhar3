package com.stackroute.operatorservice.controller;

import com.google.gson.Gson;
import com.stackroute.operatorservice.exception.BusinessException;
import com.stackroute.operatorservice.exception.ControllerException;
import com.stackroute.operatorservice.exception.NoSuchCenterExistsForIDException;
import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.service.AadharCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/aadharcenter")
public class AadharCenterCont {

    @Autowired
    private AadharCenterService ACService;
    private ResponseEntity responseEntity;

    public AadharCenterCont(){

    }
    public AadharCenterCont(AadharCenterService aadharCenterService){
        this.ACService=aadharCenterService;
    }

    @PostMapping("/saveaadharcenter")
    public ResponseEntity<?> create(@RequestParam("AadharCenterDetails") String aadharcenter, @RequestParam("file") MultipartFile file) throws IOException {
        Gson gson = new Gson();
        AadharCenterRegister aadharCenterObj = gson.fromJson(aadharcenter, AadharCenterRegister.class);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
        aadharCenterObj.setPostedDate(str);

        try{
            AadharCenterRegister ACR = ACService.create(aadharCenterObj,file);
            responseEntity = new ResponseEntity<AadharCenterRegister>(ACR, HttpStatus.CREATED);
            return responseEntity;
        }
        catch (BusinessException e){
            ControllerException ce= new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

        catch (Exception e){
            ControllerException ce = new ControllerException("611","Something went wrong in controller layer");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/getallcenters")
    public ResponseEntity<?>findAllCenter(){
        try {
            List<AadharCenterRegister>listOfAllCenters=ACService.getAllCenter();
            return new ResponseEntity<List<AadharCenterRegister>>(listOfAllCenters,HttpStatus.OK);
        }catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            ControllerException ce = new ControllerException("612","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }



    @PutMapping("/updateaadharcenter/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String centerId, @RequestParam("AadharCenterDetails") String aadharcenter, @RequestParam("file") MultipartFile file) throws IOException {
        Gson gson = new Gson();
        AadharCenterRegister aadharCenterObj = gson.fromJson(aadharcenter, AadharCenterRegister.class);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yy.MM.dd");
        String str = formatter.format(date);
        aadharCenterObj.setPostedDate(str);
        try {
            AadharCenterRegister ACR = ACService.update(centerId, aadharCenterObj,file);
            responseEntity = new ResponseEntity<AadharCenterRegister>(ACR, HttpStatus.CREATED);
            return responseEntity;
        }catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            ControllerException ce = new ControllerException("612","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }



    }


    @DeleteMapping("/deleteaadharcenter/{id}")
    public ResponseEntity<?> deleteCenterById(@PathVariable("id") String id){
        try {
            ACService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        }catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            ControllerException ce = new ControllerException("612","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("getcenterbycity/{city}")
    public ResponseEntity<?>getCenterByCity(@PathVariable("city") String city){
        try {
            List<AadharCenterRegister>listOfCenterByCity = ACService.getCenterByCity(city);
            return new ResponseEntity<List<AadharCenterRegister>>(listOfCenterByCity,HttpStatus.OK);
        }catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            ControllerException ce = new ControllerException("612","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getcenterbyid/{id}")
    public ResponseEntity<?> getCenterById(@PathVariable("id") String centerId) throws NoSuchCenterExistsForIDException {
        try {
            AadharCenterRegister centerRetrieve = ACService.getCenterById(centerId);
            return new ResponseEntity<AadharCenterRegister>(centerRetrieve, HttpStatus.OK);
        }
        catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            ControllerException ce = new ControllerException("612","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getcenterbylocationpin/{locationPin}")
    public ResponseEntity<?>findByPinCode(@PathVariable long locationPin){
        try {
            List<AadharCenterRegister> centerRetrieve = ACService.getCenterByLocationPin(locationPin);
            return new ResponseEntity<List<AadharCenterRegister>>(centerRetrieve,HttpStatus.OK);
        }catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            ControllerException ce = new ControllerException("612","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }


    }

}

