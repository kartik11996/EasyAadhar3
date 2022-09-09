package com.stackroute.customerservice.service;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.customerservice.model.CustomerList;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	
	
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
	public String updateById(String id) throws FileNotFoundException {
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
	public String save(CustomerList customer) throws FileAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerList> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CustomerList> getCustomerByMobile(String id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<CustomerList> getCustomerByEmail(String email) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public String update(String mobile, CustomerList customer) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

}