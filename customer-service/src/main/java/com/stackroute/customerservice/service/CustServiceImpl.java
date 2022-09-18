package com.stackroute.customerservice.service;

import java.util.List;
import java.util.Optional;

import com.stackroute.customerservice.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.customerservice.model.*;
import com.stackroute.customerservice.repository.*;

@Service
public class CustServiceImpl implements CustService {
	
	@Autowired
	private CustomerRepo CR;
	
	public CustServiceImpl() {

	}
	
	public CustServiceImpl(CustomerRepo customerRepo ){
        this.CR=customerRepo;
    }
	

	@Override
	public List<CustomerList> findAllCustomers() {
		return CR.findAll();
	}


	@Override
	public String saveCustomer(CustomerList customerDetails) throws CustomerAlreadyExistsException {
		 CR.save(customerDetails);
		 System.out.println("from service method " + customerDetails);
		 return "Customer has been added successfully";
	}
			
	
	@Override
	public String updateCustomerById(String id, CustomerList customerDetails) throws CustomerNotFoundException {
		CR.save(customerDetails);
		return "Customer Details are updated";
	}
	
	
	@Override
	public CustomerList getCustomerById(String id) throws CustomerNotFoundException {
		
//		return CR.findById(id);
		Optional<CustomerList> findById = CR.findById(id);
		CustomerList customer = findById.get();
		System.out.println(customer);
		return customer;
		
	}
	
	@Override
	public List<CustomerList> getCustomerByMobile(String mobile) throws CustomerNotFoundException {
		return CR.findByMobile(mobile);
	}


	@Override
	public void deleteCustomerById(String id) throws CustomerNotFoundException {
		CR.deleteById(id);
	}

}