package com.stackroute.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.services.recieptService;

import java.util.List;

@RestController
@RequestMapping("/history")
public class RecieptController {
	
	@Autowired
	private recieptService Rservice;
	
	@GetMapping("/find/{id}") 
	public Reciept home(@PathVariable String id) {
		 
		
		 
		Reciept reciept=Rservice.getPaymentDetailes(id);
		
		return reciept;
	}
	
	
	@GetMapping("/allpayment")
	public List<Reciept> findAllPayment(){
		
		List<Reciept> list=Rservice.getAllPayment();
		
		return list;
		
	}


}
