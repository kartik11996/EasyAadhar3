package com.stackroute.operatorservice.controller;

import com.google.gson.Gson;
import com.stackroute.operatorservice.configuration.RabbitMqConfiguration;
import com.stackroute.operatorservice.dto.OperatorDto;
import com.stackroute.operatorservice.exception.BusinessException;
import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.model.Appointment;
import com.stackroute.operatorservice.service.AadharCenterService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/aadharcenter")
public class AadharCenterController {


    private AadharCenterService ACService;
    private ResponseEntity responseEntity;

    @Autowired
    private RabbitTemplate template;

    public AadharCenterController(){

    }
    @Autowired
    public AadharCenterController(AadharCenterService aadharCenterService){
        this.ACService=aadharCenterService;
    }


    @PostMapping("/createBooking")
    public void createBooking(@RequestBody OperatorDto dto){



       template.convertAndSend(RabbitMqConfiguration.EXCHANGE3,
               RabbitMqConfiguration.ROUTING_KEY3,dto);


        System.out.println(dto);

    }


    @PostMapping("/saveaadharcenter")
    public ResponseEntity<?> create(@RequestParam("AadharCenterDetails") String aadharcenter, @RequestParam("file") MultipartFile file) throws IOException {
        Gson gson = new Gson();
        AadharCenterRegister aadharCenterObj = gson.fromJson(aadharcenter, AadharCenterRegister.class);
        Date date = new Date();
        //SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        //Time time = Time.valueOf(formatter.format(date));
        aadharCenterObj.setPostedDate(date);

        try{
            AadharCenterRegister ACR = ACService.create(aadharCenterObj,file);
            responseEntity = new ResponseEntity<AadharCenterRegister>(ACR, HttpStatus.CREATED);
            return responseEntity;
        }
        catch (BusinessException e){
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }

        catch (Exception e){
            return new ResponseEntity<>("Error Code: 611"+"\nError Message: Something went wrong in controller layer", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getallcenters")
    public ResponseEntity<?> findAllCenter(){
        try {
            List<AadharCenterRegister>listOfAllCenters=ACService.getAllCenter();
            return new ResponseEntity<List<AadharCenterRegister>>(listOfAllCenters,HttpStatus.OK);
        }catch (BusinessException e) {
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
            return new ResponseEntity<>("Error Code: 612"+"\nError Message: Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateaadharcenter/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String centerId, @RequestParam("AadharCenterDetails") String aadharcenter, @RequestParam("file") MultipartFile file) throws IOException {
        Gson gson = new Gson();
        AadharCenterRegister aadharCenterObj = gson.fromJson(aadharcenter, AadharCenterRegister.class);
        Date date = new Date();
       // SimpleDateFormat formatter = new SimpleDateFormat("yy.MM.dd");
        //String str = formatter.format(date);
        aadharCenterObj.setPostedDate(date);
        try {
            AadharCenterRegister ACR = ACService.update(centerId, aadharCenterObj,file);
            responseEntity = new ResponseEntity<AadharCenterRegister>(ACR, HttpStatus.CREATED);
            return responseEntity;
        }
        /*catch (NoSuchElementException e){
            return new ResponseEntity<>("Error Code: 623"+"\nError Message: Id does not exist in database", HttpStatus.BAD_REQUEST);
        }

*/     catch (NoSuchElementException e){
            return  new ResponseEntity<>("Error Code: 618"+"\nError Messagee: No center Exits with this id, Please choose another center ID", HttpStatus.CONFLICT);
        }
        catch (BusinessException e) {
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
            return new ResponseEntity<>("Error Code: 613"+"\nError Message: Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteaadharcenter/{id}")
    public ResponseEntity<?> deleteCenterById(@PathVariable("id") String id){
        try {
            ACService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        }catch (BusinessException e) {
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
            return new ResponseEntity<>("Error Code: 614"+"\nError Message: Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("getcenterbycity/{city}")
    public ResponseEntity<?> getCenterByCity(@PathVariable("city") String city){
        try {
            List<AadharCenterRegister>listOfCenterByCity = ACService.getCenterByCity(city);
            return new ResponseEntity<List<AadharCenterRegister>>(listOfCenterByCity,HttpStatus.OK);
        }catch (BusinessException e) {
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
            return new ResponseEntity<>("Error Code: 615"+"\nError Message: Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getcenterbyid/{id}")
    public ResponseEntity<?> getCenterById(@PathVariable("id") String centerId) {
        try {
            AadharCenterRegister centerRetrieve = ACService.getCenterById(centerId);
            return new ResponseEntity<AadharCenterRegister>(centerRetrieve, HttpStatus.OK);
        }
        catch (BusinessException e){
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>("StatusCode: 616,"+"\nError Message:Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getcenterbylocationpin/{locationPin}")
    public ResponseEntity<?> findByPinCode(@PathVariable long locationPin){
        try {
            List<AadharCenterRegister> centerRetrieve = ACService.getCenterByLocationPin(locationPin);
            return new ResponseEntity<List<AadharCenterRegister>>(centerRetrieve,HttpStatus.OK);
        }catch (BusinessException e) {
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
          //  ControllerException ce = new ControllerException();
            return new ResponseEntity<>("Error Code: 617"+"\nError Message: Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
    }

   @PostMapping("/createslots/{id}")
    public ResponseEntity<?> createSlots(@PathVariable("id") String id, @RequestBody Appointment appointment){
        try {
            Appointment appointment1 = ACService.createAppointment(id, appointment);
            return new ResponseEntity<Appointment>(appointment1, HttpStatus.CREATED);
        }catch (BusinessException e) {
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
            //  ControllerException ce = new ControllerException();
            return new ResponseEntity<>("Error Code: "+"\nError Message: Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
   }
    @DeleteMapping("/deleteallslots/{id}")
    public ResponseEntity<?> deleteSlots(@PathVariable("id") String id){
        try {
            ACService.deleteAppointment(id);
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        }catch (BusinessException e) {
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
            //  ControllerException ce = new ControllerException();
            return new ResponseEntity<>("Error Code: "+"\nError Message: Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getallslots/{id}")
    public ResponseEntity<?> findAllslots(@PathVariable("id") String id){
        try {
            List<Appointment>listOfAllslots=ACService.getAllSlots(id);
            return new ResponseEntity<List<Appointment>>(listOfAllslots,HttpStatus.OK);
        }catch (BusinessException e) {
            return new ResponseEntity<>("Error Code: "+e.getErrorCode()+"\nError Message:"+e.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
            return new ResponseEntity<>("Error Code: 612"+"\nError Message: Something went wrong in controller", HttpStatus.BAD_REQUEST);
        }
    }
}

