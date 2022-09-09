package com.stackroute.customerservice.service;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

import com.stackroute.customerservice.model.CustomerList;

public interface CustomerService {
	
	
	Iterable<CustomerList> getAllCustomers();
	
	//Create
	CustomerList saveCustomerList(CustomerList customerList) throws FileAlreadyExistsException;

	//Update
	String updateById(String id) throws FileNotFoundException;

//	String updateCustomer(CustomerList customerList) throws FileNotFoundException;
//	CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException;
	
	//Read
	CustomerList getCustomerById(String id)	;
		
	//Delete
	void deleteById(String id);


	

//	public boolean createCustomerList(CustomerList CustomerList);



}
