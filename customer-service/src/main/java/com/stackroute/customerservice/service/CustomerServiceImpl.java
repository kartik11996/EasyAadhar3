package com.stackroute.customerservice.service;

import java.util.List;
import java.util.Optional;

import com.stackroute.customerservice.configuration.RabbitMqConfiguration;
import com.stackroute.customerservice.dto.CustomerDto;
import com.stackroute.customerservice.exception.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.customerservice.model.*;
import com.stackroute.customerservice.repository.*;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository CR;

	@Autowired
	private RabbitTemplate template;
	
	public CustomerServiceImpl() {

	}
	
	public CustomerServiceImpl(CustomerRepository customerRepository){
        this.CR= customerRepository;
    }
	

	private String emailId;
	@RabbitListener(queues =  RabbitMqConfiguration.QUEUE)
	public void consumeLoanDetailsFromQueue(String email) {
		emailId=email;
		System.out.println("User details recieved from queue : " + email);

	}
	@Override
	public String saveCustomer(Customer customer) throws CustomerAlreadyExistsException {
		customer.setEmail(emailId);
		CustomerDto customer1=new CustomerDto(customer.getEmail(),customer.getName(),customer.getMobile());

		template.convertAndSend(RabbitMqConfiguration.EXCHANGE,RabbitMqConfiguration.ROUTING_KEY2,customer1);

		 CR.save(customer);
		 System.out.println("from service method " + customer);
		 return "Customer has been added successfully";
	}


	@Override
	public List<Customer> getAllCustomers() {
		return CR.findAll();
	}




	@Override
	public String updateCustomerById(String id, Customer customerDetails) throws CustomerNotFoundException {
		Optional<Customer> findById = CR.findById(id);
		Customer customer = findById.get();
//		CustomerList customer = new CustomerList();
		if (findById.isPresent()){
			customer.setName(customerDetails.getName());
			customer.setEmail(customerDetails.getEmail());
			customer.setAddress(customerDetails.getAddress());
			customer.setDOB(customerDetails.getDOB());
			customer.setMobile(customerDetails.getMobile());
			customer.setGender(customerDetails.getGender());
			customer.setNationality(customerDetails.getNationality());
			customer.setParentName(customerDetails.getParentName());
			customer.setRelativeAadharNumber(customerDetails.getRelativeAadharNumber());
			customer.setTypeOfRelation(customerDetails.getTypeOfRelation());

		}
		CR.save(customer);
		return "Customer Details are updated";
	}
	
	
	@Override
	public Customer getCustomerById(String id) throws CustomerNotFoundException {
		
//		return CR.findById(id);
		Optional<Customer> findById = CR.findById(id);
		Customer customer = findById.get();
		System.out.println(customer);
		return customer;
		
	}
	
	@Override
	public List<Customer> getCustomerByMobile(String mobile) throws CustomerNotFoundException {
		return CR.findByMobile(mobile);
	}


	@Override
	public boolean deleteCustomerById(String id) throws CustomerNotFoundException {
		CR.deleteById(id);

		return true;
	}

}