package com.stackroute.customerservice.service;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Optional;

import com.stackroute.customerservice.model.CustomerList;

public interface CustomerService {
	
	
//	Iterable<CustomerList> getAllCustomers();
	
	List<CustomerList> findAll();
	
	String save(CustomerList CustomerDetails) throws FileAlreadyExistsException;

//	CustomerList save(String id, CustomerList CustomerDetails) throws FileAlreadyExistsException;
	
//	CustomerList saveCustomerList(CustomerList customerList) throws FileAlreadyExistsException;
	
	String updateById(String id, CustomerList CustomerDetails) throws FileNotFoundException;

//	CustomerList updateById(String id, CustomerList CustomerDetails) throws FileNotFoundException;

//	CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException;
	
	Optional<CustomerList> getCustomerById(String id) throws FileNotFoundException;	

	Optional<CustomerList> getCustomerByMobile(String mobile) throws FileNotFoundException;

	Optional<CustomerList> getCustomerByEmail(String email) throws FileNotFoundException;

	void deleteById(String id);

//	String save(String id, CustomerList CustomerDetails) throws FileAlreadyExistsException;

//	String updateById(String id, CustomerList customer) throws FileNotFoundException;


}
