package com.stackroute.customerservice.service;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
//import java.util.Optional;

import com.stackroute.customerservice.model.CustomerList;

public interface CustomerService {
	
	
	List<CustomerList> findAllCustomers();
	
	String saveCustomer(CustomerList CustomerDetails) throws FileAlreadyExistsException;
	
	String updateById(String id, CustomerList CustomerDetails) throws FileNotFoundException;

	CustomerList getCustomerById(String id) throws FileNotFoundException;	

	List<CustomerList> getCustomerByMobile(String mobile) throws FileNotFoundException;

//	List<CustomerList> getCustomerByEmail(String email) throws FileNotFoundException;

	void deleteById(String id);
	
//	Iterable<CustomerList> getAllCustomers();

//	CustomerList save(String id, CustomerList CustomerDetails) throws FileAlreadyExistsException;
	
//	CustomerList saveCustomerList(CustomerList customerList) throws FileAlreadyExistsException;

//	String save(String id, CustomerList CustomerDetails) throws FileAlreadyExistsException;

//	String updateById(String id, CustomerList customer) throws FileNotFoundException;

//	CustomerList updateById(String id, CustomerList CustomerDetails) throws FileNotFoundException;

//	CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException;


}
