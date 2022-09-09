package com.stackroute.customerservice.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.customerservice.model.*;
import com.stackroute.customerservice.repository.*;
import com.stackroute.customerservice.service.CustomerService;

@RestController
public class CustomerController { 

	private CustomerService customerService;
    private CustomerRepo repo;
    
    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepo repo) {
        this.customerService = customerService;
        this.repo = repo;
    }
    
    @PostMapping("/addCustomer")
    public String saveCustomer(@RequestBody CustomerList customerList) {
        repo.save(customerList); 													
        return "Customer has been added successfully";
    }

    @GetMapping("/findAllCustomer")
    public List<CustomerList> getAllCustomers() {
        return repo.findAll();
    }
    
    @GetMapping("/findAllCustomer/{id}")
    public Optional<CustomerList> getCustomer(@PathVariable String Id) {
    	return repo.findById(Id);
    }

    @PutMapping("/update/{id}")
    public String updateCustomer(@RequestBody CustomerList customerList) throws FileNotFoundException {
        return customerService.updateCustomer(customerList);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable String Id) {
        repo.deleteById(Id);
        return "The customer has been deleted successfully";
    }

}
