package com.stackroute.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.stackroute.paymentservice.exception.RecieptNotFoundException;

import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.services.recieptService;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/history")
public class RecieptController {
	
	@Autowired
	private recieptService Rservice;
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getPaymentDetailes(@PathVariable String id) {
		try {
			return new ResponseEntity<>(Rservice.getPaymentDetails(id), HttpStatus.OK);

		} catch (RecieptNotFoundException e) {
			return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Get payment details by id" , notes = "Return the payment details as per the id")
	
	@GetMapping("/allpayment")
	public ResponseEntity<?> getAllPayment() {
		try {
			return new ResponseEntity<>(Rservice.getAllPayment(), HttpStatus.OK);

		} catch (RecieptNotFoundException e) {
			return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CONFLICT);
		}
	}
}

