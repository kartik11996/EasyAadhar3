package com.stackroute.authenticationservice.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.service.UserService;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public ResponseEntity<?> registerNewUser(@RequestBody User user) throws UserAlreadyExistException {
    	try {
    		
        return new ResponseEntity<>( userService.registerNewUser(user),HttpStatus.OK);
        
    	}catch(UserAlreadyExistException e) {
    		return new ResponseEntity<>( e.getMsg(),HttpStatus.CONFLICT);
    	}
    }

    @PostMapping({"/registerNewOperator"})
   
    public ResponseEntity<?> registerNewOperator(@RequestBody User user) throws UserAlreadyExistException {
    	try {
    		
        return new ResponseEntity<>( userService.registerNewOperator(user),HttpStatus.OK);
        
    	}catch(UserAlreadyExistException e) {
    		return new ResponseEntity<>( e.getMsg(),HttpStatus.CONFLICT);
    	}
    }
    
    
    @GetMapping({"/operator"})
    @PreAuthorize("hasRole('Operator')")
    public String forAdmin(){
        return "welcome in Operator board";
    }

    
    @GetMapping({"/user"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "welcome in user board";
    }
}
