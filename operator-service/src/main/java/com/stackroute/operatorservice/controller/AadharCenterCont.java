package com.stackroute.operatorservice.controller;

import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.repository.AadharCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

@SpringBootApplication
@RestController
public class AadharCenterCont {
    @Autowired
    private AadharCenterRepo ACRepo;

    @PostMapping("/saveAadharcenter")
    public int saveAadharCenter(@RequestBody List<AadharCenterRegister> AadharCenter){
        System.out.println(AadharCenter.toString());
        ACRepo.saveAll(AadharCenter);
        return AadharCenter.size();
    }

    @GetMapping("/getAllAadharCenter")
    public Iterable<AadharCenterRegister>findAllCenter(){
        return ACRepo.findAll();
    }

    @GetMapping("/getCenterByCity/city")
    public List<AadharCenterRegister>findByCity(@PathVariable String city){
        return ACRepo.findByCity(city);
    }



}
