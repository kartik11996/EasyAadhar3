package com.stackroute.customerservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.customerservice.model.CustomerList;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerList, String> {

	List<CustomerList> findByName(String name);
	
	List<CustomerList> findByEmail(String email);

	List<CustomerList> findByMobile(String mobile);

	List<CustomerList> findByAddress(String address);

//	String updateCustomer(CustomerList customerList);

}
