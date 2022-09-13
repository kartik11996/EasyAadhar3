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
	

	@Override
	public List<CustomerList> findAllCustomers() {
		return CR.findAll();
	}
	

	@Override
	public String saveCustomer(CustomerList customerDetails) throws FileAlreadyExistsException {
		 CR.save(customerDetails);
		 System.out.println("from service method " + customerDetails);
		 return "Customer has been added successfully";
	}
			
	
	@Override
	public String updateById(String id, CustomerList customerDetails) throws FileNotFoundException {
		CR.save(customerDetails);
		return "Customer Details are updated";
	}
	
	
	@Override
	public CustomerList getCustomerById(String id) throws FileNotFoundException {
		
//		return CR.findById(id);
		Optional<CustomerList> findById = CR.findById(id);
		CustomerList customer = findById.get();
		System.out.println(customer);
		return customer;
		
	}
	
	@Override
	public List<CustomerList> getCustomerByMobile(String mobile) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return CR.findByMobile(mobile);
	}

//	@Override
//	public List<CustomerList> getCustomerByEmail(String email) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		return CR.findByEmail(email);
//	}

	@Override
	public void deleteById(String id) {
		CR.deleteById(id);
	}

	
	
//	@Override
//	public Iterable<CustomerList> getAllCustomers() {
//		return CR.findAll();
//	}
	
	
	
//	@Override
//	public CustomerList updateCustomerList(CustomerList customerList) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}


//	@Override
//	public CustomerList save(String id, CustomerList CustomerDetails) throws FileAlreadyExistsException {
//		return CR.save(CustomerDetails);
//	}
	
//	@Override
//	public S save(String id, CustomerList CustomerDetails) throws FileAlreadyExistsException {
//		// TODO Auto-generated method stub
//		return CR.save(id);
//	}

//	@Override
//	public String update(String mobile, CustomerList customer) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

}