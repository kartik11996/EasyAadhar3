package com.stackroute.customerservice.service;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.customerservice.model.CustomerList;
import com.stackroute.customerservice.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo CR;
	
	public CustomerServiceImpl() {
		
	}
	
	public CustomerServiceImpl(CustomerRepo customerRepo ){
        this.CR=customerRepo;
    }

	
//	@Override
//	public Iterable<CustomerList> getAllCustomers() {
//		return CR.findAll();
//	}
	

	@Override
	public List<CustomerList> findAll() {
		return CR.findAll();
	}
	
//	@Override
//	public CustomerList save(String id, CustomerList CustomerDetails) throws FileAlreadyExistsException {
//		return CR.save(CustomerDetails);
//	}
	
//	@Override
//	public S save(String id, CustomerList CustomerDetails) throws FileAlreadyExistsException {
//		// TODO Auto-generated method stub
//		return CR.save(id);
//	}

	@Override
	public String save(CustomerList customerDetails) throws FileAlreadyExistsException {
		return CR.save(customerDetails);
	}
			
	
	@Override
	public String updateById(String id, CustomerList CustomerDetails) throws FileNotFoundException {
		return CR.save(CustomerDetails);
	}
	
	
	@Override
	public Optional<CustomerList> getCustomerById(String id) throws FileNotFoundException {
		return CR.findById(id);
	}
	
	@Override
	public Optional<CustomerList> getCustomerByMobile(String mobile) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return CR.findByMobile(mobile);
	}

	@Override
	public Optional<CustomerList> getCustomerByEmail(String email) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return CR.findByEmail(email);
	}

	@Override
	public void deleteById(String id) {
		CR.deleteById(id);
	}


	
//	@Override
//	public CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}


	

//	@Override
//	public String update(String mobile, CustomerList customer) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

}