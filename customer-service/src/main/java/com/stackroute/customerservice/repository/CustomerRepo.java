package com.stackroute.customerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.customerservice.model.CustomerList;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerList, Integer> {

	String updateCustomer(CustomerList customerList);


}
