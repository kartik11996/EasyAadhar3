package com.stackroute.customerservice.service;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Optional;

import com.stackroute.customerservice.model.CustomerList;

public interface CustomerService {
	
	
	Iterable<CustomerList> getAllCustomers();
	
	String save(CustomerList customer) throws FileAlreadyExistsException;

	String updateById(String id) throws FileNotFoundException;

	CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException;
	
	CustomerList getCustomerById(String id);	

	List<CustomerList> findAll();

	Optional<CustomerList> getCustomerByMobile(String mobile) throws FileNotFoundException;

	Optional<CustomerList> getCustomerByEmail(String email) throws FileNotFoundException;

	CustomerList saveCustomerList(CustomerList customerList) throws FileAlreadyExistsException;

	String update(String mobile, CustomerList customer) throws FileNotFoundException;

	void deleteById(String id);


}
