package com.stackroute.customerservice.controller;

//import java.nio.file.FileAlreadyExistsException;
import java.util.List;
//import java.util.Optional;

import com.stackroute.customerservice.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.customerservice.model.*;
import com.stackroute.customerservice.service.*;

@SpringBootApplication
@RestController
@RequestMapping("/CustomerDetails")
public class CustomerController {
	
	private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerList customer) {
        try {
            if (customer.getName() == null
                    || customer.getEmail() == null
                    || customer.getMobile() == null
                    || customer.getAddress() == null
                    || customer.getNationality() == null
//                    || customer.getDOB() == null
                    || customer.getParentName() == null
                    || customer.getTypeOfRelation() == null
                    || customer.getRelativeAadharNumber().isEmpty()) {
                return new ResponseEntity<>("Please enter all the required fields", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);

        } catch (CustomerAlreadyExistsException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CONFLICT);
        }
    }

    
    @GetMapping("/findAllCustomers")
    List<CustomerList>getAllCustomers(){
        return customerService.findAllCustomers();
    }


    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);

        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody CustomerList customer) {
//        System.out.println(id);
        try {
            if (customer.getName() == null
                    || customer.getEmail() == null
                    || customer.getMobile() == null
                    || customer.getAddress() == null
                    || customer.getNationality() == null
//                    || customer.getDOB() == null
                    || customer.getParentName() == null
                    || customer.getTypeOfRelation() == null
                    || customer.getRelativeAadharNumber().isEmpty()) {
                return new ResponseEntity<>("Please enter all the required fields", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(customerService.updateCustomerById(id, customer), HttpStatus.CREATED);

        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CONFLICT);
        }
    }

    
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable String id) {
        try {
            customerService.deleteCustomerById(id);
            return new ResponseEntity<>("Customer Details deleted from the database with Email Id: "+id, HttpStatus.OK);

        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CONFLICT);
        }
    }

}
