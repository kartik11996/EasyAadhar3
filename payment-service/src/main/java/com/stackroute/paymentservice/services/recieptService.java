package com.stackroute.paymentservice.services;

import java.util.List;

import com.stackroute.paymentservice.model.Reciept;

public interface recieptService {


	List<Reciept> getAllPayment();

	Reciept getPaymentDetails(String id);
 
	}


