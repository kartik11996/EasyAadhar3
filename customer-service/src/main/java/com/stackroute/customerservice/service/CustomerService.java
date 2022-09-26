package com.stackroute.customerservice.service;

import java.util.List;
//import java.util.Optional;

import com.stackroute.customerservice.exception.*;
//import com.stackroute.customerservice.model.CustomerList;


public interface CustomerService<CustomerList> {
	
	
	List<CustomerList> getAllCustomers();

	String saveCustomer(com.stackroute.customerservice.model.CustomerList customerDetails) throws CustomerAlreadyExistsException;

	String updateCustomerById(String id, com.stackroute.customerservice.model.CustomerList customerDetails) throws CustomerNotFoundException;

	CustomerList getCustomerById(String id) throws CustomerNotFoundException;

	boolean deleteCustomerById(String id) throws CustomerNotFoundException;

	List<CustomerList> getCustomerByMobile(String mobile) throws CustomerNotFoundException;

//	String saveCustomer(CustomerList CustomerDetails) throws CustomerAlreadyExistsException;
//
//	String updateCustomerById(String id, CustomerList CustomerDetails) throws CustomerNotFoundException;

}
