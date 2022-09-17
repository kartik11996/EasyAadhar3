package com.stackroute.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.paymentservice.model.Reciept;

@Repository
public interface PaymentRepository extends JpaRepository<Reciept, String> {
	

}
