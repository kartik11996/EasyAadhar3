package com.stackroute.paymentservice.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.paymentservice.exception.RecieptNotFoundException;
import com.stackroute.paymentservice.model.Reciept;
import com.stackroute.paymentservice.repository.PaymentRepository;

@Service
public class recieptServiceImpl implements recieptService {
	
	@Autowired
	private PaymentRepository Prepo;

    @Override
	public  Reciept getPaymentDetails(String id) {
		Optional< Reciept> optional = Prepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new RecieptNotFoundException("Reciept is not found");
		}

	}

	@Override
	
	
	public List<Reciept> getAllPayment() {

		List<Reciept> list = Prepo.findAll();
		if (list.size() == 0) {
			throw new RecieptNotFoundException("Reciept is not available in the database");
		}
		return list;
	}


}
