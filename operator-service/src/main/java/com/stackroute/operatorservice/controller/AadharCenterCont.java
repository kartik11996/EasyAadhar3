package com.stackroute.operatorservice.controller;

import com.google.gson.Gson;
import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.service.AadharCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/aadharcenter")
public class AadharCenterCont {

    @Autowired
    private AadharCenterService ACService;

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
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YY");
        //Date str = formatter.format(date);
        aadharCenterObj.setPostedDate(date);

        AadharCenterRegister ACR = ACService.create(aadharCenterObj,file);
        ResponseEntity<AadharCenterRegister> responseEntity = new ResponseEntity<>(ACR, HttpStatus.CREATED);
        return responseEntity;

    }

    @GetMapping("/findallcenters")
    List<AadharCenterRegister>findAllCenter(){
        return ACService.findAll();
    }


    @PutMapping("/updateaadharcenter/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String centerId, @RequestParam("AadharCenterDetails") String aadharcenter, @RequestParam("file") MultipartFile file) throws IOException {
        Gson gson = new Gson();
        AadharCenterRegister aadharCenterObj = gson.fromJson(aadharcenter, AadharCenterRegister.class);
        Date date = new Date();
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YY");
        //Date str = formatter.format(date);
        aadharCenterObj.setPostedDate(date);


        AadharCenterRegister ACR = ACService.update(centerId, aadharCenterObj,file);
        ResponseEntity<AadharCenterRegister> responseEntity = new ResponseEntity<>(ACR, HttpStatus.CREATED);
        return responseEntity;
    }


    @DeleteMapping("/deleteaadharcenter/{id}")
    String deleteById(@PathVariable String id){
        try{
            return ACService.deleteById(id);
        }
        catch(Exception e){
            return "No such center exits with this id";
        }
    }

    @GetMapping("getcenterbycity/{city}")
    List<AadharCenterRegister>findByCity(@PathVariable String city){
        return ACService.findByCity(city);
    }

    @GetMapping("/getcenterbyid/{id}")
    public AadharCenterRegister getAadharCenter(@PathVariable("id") String centerId){
        return ACService.getAadharCenter(centerId);
    }

    @GetMapping("/getcenterbylocationpin/{locationPin}")
    List<AadharCenterRegister>findByPinCode(@PathVariable long locationPin){
        return ACService.findBylocationPin(locationPin);
    }




}
