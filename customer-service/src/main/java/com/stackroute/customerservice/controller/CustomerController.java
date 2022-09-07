package com.stackroute.customerservice.controller;

import java.util.List;

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
import com.stackroute.customerservice.service.CustService;

@RestController
public class CustomerController { 

	private CustService custService;				// create service and call the function
    private CustomerRepo repo;

    @Autowired
    public CustomerController(CustService custService) {
    	this.custService = custService;
    }
    
    @PostMapping("/addCustomer")
    public String saveCustomer(@RequestBody CustomerList customerList) {
        repo.save(customerList); 													
        return "Customer has been added successfully";
    }

    @GetMapping("/findAllCustomer")
    public List<CustomerList> getAllcustomers() {
        return repo.findAll();
    }

    @PutMapping("/update/{id}")
    public String updateCustomer(@RequestBody CustomerList customerList) {
        return repo.updateCustomer(customerList);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int Id) {
        repo.deleteById(Id);
        return "The customer has been deleted successfully";
    }

}
