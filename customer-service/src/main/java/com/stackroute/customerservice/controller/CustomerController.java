package com.stackroute.customerservice.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.nio.file.FileAlreadyExistsException;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.customerservice.model.*;
import com.stackroute.customerservice.service.CustomerService;

@SpringBootApplication
@RestController
@RequestMapping("/CustomerDetails")
public class CustomerController { 
	
	@Autowired
	private CustomerService customerService;
	

    public CustomerController(CustomerService customerService	) {
        this.customerService = customerService;
    }
    
	// test in postman
	
    
    @PostMapping("/addCustomer")
    public String save(@RequestBody CustomerList CustomerDetails) throws IOException{
    	System.out.println(CustomerDetails);
//        try {
//			return customerService.save(CustomerDetails);
//		} catch (FileAlreadyExistsException e) {
//			e.printStackTrace();
//		} 
    	customerService.saveCustomer(CustomerDetails);
        return "Customer has been added successfully";

    }
        

    
    @GetMapping("/findAllCustomers")
    List<CustomerList>getAllCenter(){
        return customerService.findAllCustomers();
    }
    

    @GetMapping("/findCustomer/{id}")
    public CustomerList getCustomerById(@PathVariable("id") String id){
    	System.out.println(id);
        try {
			return customerService.getCustomerById(id);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
    }

//    @GetMapping("/findAllCustomers/{id}")
//    public Optional<CustomerList>getCustomerByEmail(@PathVariable("id") String email){
//        try {
//			return customerService.getCustomerByEmail(email);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//    }

    
    @PutMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable("id") String id, @RequestBody CustomerList customerDetails){
        try {
			return customerService.updateById(id, customerDetails);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Customer Details are updated";
    }

    
    
    @DeleteMapping("/deleteCustomer/{id}")
    void deleteById(@PathVariable String id){
    	customerService.deleteById(id);
    }


}
