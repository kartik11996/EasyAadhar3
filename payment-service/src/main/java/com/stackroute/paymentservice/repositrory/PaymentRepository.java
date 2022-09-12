package com.stackroute.paymentservice.repositrory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.paymentservice.model.PaymentHistory;

public interface PaymentRepository extends JpaRepository<PaymentHistory, Long> {

}
