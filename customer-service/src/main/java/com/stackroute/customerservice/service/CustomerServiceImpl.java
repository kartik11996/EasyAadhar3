package com.stackroute.customerservice.service;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

import org.springframework.stereotype.Service;

import com.stackroute.customerservice.model.CustomerList;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Override
	public Iterable<CustomerList> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerList saveCustomerList(CustomerList customerList) throws FileAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
	public String updateCustomer(CustomerList customerList) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CustomerList getCustomerById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

	
//	public CustServiceImpl() {
//		super();
//
//	}

//	@Override
//	public boolean createCustomerList(CustomerList CustomerList) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
//	@Override
//	public void deleteCustomer(String id) {
//		CustomerRepo.deleteById(id);
//	}

//	@Override
//	public String updateCustomer(CustomerList customerList) {
//		return null;
//	}

//	@Override
//	public CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException {
//		if(customerRepo.findById(customerList.getMobile()).isEmpty()) {
//			throw new FileNotFoundException();
//		}
//		return CustomerRepo.save(customerList);
//	}
//
//	@Override
//	public Iterable<CustomerList> getAllCustomers() {
//		// TODO Auto-generated method stub
//		return CustomerRepo.findAll();
//	}


}
