package com.stackroute.operatorservice.controller;

import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.repository.AadharCenterRepo;
import com.stackroute.operatorservice.service.AadharCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

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
    public AadharCenterRegister create(@RequestBody AadharCenterRegister aadharcenter){
        return ACService.create(aadharcenter);
    }

    @GetMapping("/findallcenters")
    List<AadharCenterRegister>findAllCenter(){
        return ACService.findAll();
    }
    @PutMapping("/updateaadharcenter/{id}")
    public AadharCenterRegister update(@PathVariable String centerId, @RequestBody AadharCenterRegister aadharcenter){
        return ACService.update(centerId, aadharcenter);
    }



    @DeleteMapping("/deleteaadharcenter/{id}")
    void deleteById(@PathVariable String id){
        ACService.deleteById(id);
    }


    @GetMapping("getcenterbycity/{city}")
    List<AadharCenterRegister>findByCity(@PathVariable String city){
        return ACService.findByCity(city);
    }

    @GetMapping("/getaadharcenter/{id}")
    Optional<AadharCenterRegister>getAadharCenter(String centerId){
        return ACService.getAadharCenter(centerId);
    }





}
