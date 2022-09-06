package com.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customerservice.model.CustomerList;
import com.customerservice.repository.CustomerRepo;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepo repo;

    @PostMapping("/addCustomer")
    public String saveCustomer(@RequestBody CustomerList customerList) {
        repo.save(customerList);
        return "Customer has been added successfully";
    }

    @GetMapping("/findAllCustomer")
    public List<CustomerList> getAllcustomers() {
        return repo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int Id) {
        repo.deleteById(Id);
        return "The customer has been deleted successfully";
    }

}
