package com.stackroute.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.authenticationservice.exception.InvalidCredentialException;
import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.model.JwtRequest;
import com.stackroute.authenticationservice.model.JwtResponse;
import com.stackroute.authenticationservice.service.JwtService;


@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public ResponseEntity<?> createJwtToken(@RequestBody JwtRequest jwtRequest)  {
    	
        try {
			return new ResponseEntity<>(jwtService.createJwtToken(jwtRequest),HttpStatus.OK);
		} catch (InvalidCredentialException e) {
			return new ResponseEntity<>(e.getErrorMessage(),HttpStatus.OK);
		}
    }
}
