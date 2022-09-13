package com.stackroute.paymentservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.repositrory.PaymentRepository;

@Service
public class recieptServiceImpl implements recieptService {
	
	@Autowired
	private PaymentRepository Prepo;

	@Override
	public Reciept getPaymentDetailes(String id) {

		Optional<Reciept> find= Prepo.findById(id);
		
		return find.get();
	}

	@Override
	public List<Reciept> getAllPayment() {

		List<Reciept> list = Prepo.findAll();
		return list;
	}
	

}
