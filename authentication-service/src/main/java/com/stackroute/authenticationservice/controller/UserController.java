package com.stackroute.authenticationservice.controller;

import javax.annotation.PostConstruct;

//import com.stackroute.authenticationservice.configuration.RabbitMqConfiguration;
import com.stackroute.authenticationservice.configuration.RabbitMqConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

   @Autowired
   private RabbitTemplate template;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }


    @PostMapping({"/registerNewUser"})
    public ResponseEntity<?> registerNewUser(@RequestParam String userName, @RequestParam String userPassword) throws UserAlreadyExistException {
    	try {
            User user=userService.registerNewUser(userName,userPassword);
         template.convertAndSend(RabbitMqConfiguration.EXCHANGE,RabbitMqConfiguration.ROUTING_KEY,userName);
            return new ResponseEntity<>(user ,HttpStatus.OK);
        
    	}catch(UserAlreadyExistException e) {
    		return new ResponseEntity<>( e.getMsg(),HttpStatus.CONFLICT);
    	}catch(Exception e){
            return new ResponseEntity<>( "internal server error",HttpStatus.CONFLICT);

        }
    }

    @PostMapping({"/registerNewOperator"})
   
    public ResponseEntity<?> registerNewOperator(@RequestParam String userName, @RequestParam String userPassword) throws UserAlreadyExistException {
    	try {
            User user=userService.registerNewOperator(userName,userPassword);
            template.convertAndSend(RabbitMqConfiguration.EXCHANGE,RabbitMqConfiguration.ROUTING_KEY1,userName);
        return new ResponseEntity<>( user,HttpStatus.OK);
        
    	}catch(UserAlreadyExistException e) {
    		return new ResponseEntity<>( e.getMsg(),HttpStatus.CONFLICT);
    	}catch(Exception e){
           return new ResponseEntity<>( "internal server error",HttpStatus.CONFLICT);

        }
    }
    
    
    @GetMapping({"/operator"})
    @PreAuthorize("hasRole('Operator')")
    public String forOperator(){
        return "welcome in Operator board";
    }


    @GetMapping({"/user"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "welcome in user board";
    }
}
