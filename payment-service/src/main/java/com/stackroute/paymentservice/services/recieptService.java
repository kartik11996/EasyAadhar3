package com.stackroute.paymentservice.services;

import java.util.List;

import com.stackroute.paymentservice.model.Reciept;

public interface recieptService {

	Reciept getPaymentDetailes(String id);

	List<Reciept> getAllPayment();
 
	}


