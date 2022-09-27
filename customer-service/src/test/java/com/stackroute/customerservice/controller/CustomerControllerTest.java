package com.stackroute.customerservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.customerservice.exception.CustomerAlreadyExistsException;
import com.stackroute.customerservice.exception.CustomerNotFoundException;
import com.stackroute.customerservice.model.Customer;
import com.stackroute.customerservice.repository.CustomerRepository;
import com.stackroute.customerservice.service.CustomerService;
import com.stackroute.customerservice.service.CustomerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CustomerControllerTest {

    @Test
    void testAddCustomer() {
        CustomerController customerController = new CustomerController(new CustomerServiceImpl());

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualAddCustomerResult = customerController.addCustomer(customer);
        assertNull(actualAddCustomerResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualAddCustomerResult.getStatusCode());
        assertTrue(actualAddCustomerResult.getHeaders().isEmpty());
    }


    @Test
    void testAddCustomer2() {

        CustomerController customerController = new CustomerController(null);

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualAddCustomerResult = customerController.addCustomer(customer);
        assertNull(actualAddCustomerResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualAddCustomerResult.getStatusCode());
        assertTrue(actualAddCustomerResult.getHeaders().isEmpty());
    }


    @Test
    void testAddCustomer3() {

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        CustomerController customerController = new CustomerController(new CustomerServiceImpl(customerRepository));

        Customer customer1 = new Customer();
        customer1.setAddress("42 Main St");
        customer1.setDOB("D OB");
        customer1.setEmail("jane.doe@example.org");
        customer1.setGender("Gender");
        customer1.setMobile("Mobile");
        customer1.setName("Name");
        customer1.setNationality("Nationality");
        customer1.setParentName("Parent Name");
        customer1.setRelativeAadharNumber("42");
        customer1.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualAddCustomerResult = customerController.addCustomer(customer1);
        assertEquals("Customer has been added successfully", actualAddCustomerResult.getBody());
        assertEquals(HttpStatus.CREATED, actualAddCustomerResult.getStatusCode());
        assertTrue(actualAddCustomerResult.getHeaders().isEmpty());
        verify(customerRepository).save((Customer) any());
    }


    @Test
    void testAddCustomer4() throws CustomerAlreadyExistsException {

        CustomerService customerService = mock(CustomerService.class);
        when(customerService.saveCustomer((Customer) any()))
                .thenThrow(new CustomerAlreadyExistsException("An error occurred"));
        CustomerController customerController = new CustomerController(customerService);

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualAddCustomerResult = customerController.addCustomer(customer);
        assertEquals("An error occurred", actualAddCustomerResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualAddCustomerResult.getStatusCode());
        assertTrue(actualAddCustomerResult.getHeaders().isEmpty());
        verify(customerService).saveCustomer((Customer) any());
    }




    @Test
    void testGetAllCustomers() {

        CustomerRepository customerRepository = mock(CustomerRepository.class);
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> actualAllCustomers = (new CustomerController(new CustomerServiceImpl(customerRepository)))
                .getAllCustomers();
        assertSame(customerList, actualAllCustomers);
        assertTrue(actualAllCustomers.isEmpty());
        verify(customerRepository).findAll();
    }


    @Test
    void testGetCustomerById() {

        ResponseEntity<?> actualCustomerById = (new CustomerController(new CustomerServiceImpl())).getCustomerById("42");
        assertNull(actualCustomerById.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualCustomerById.getStatusCode());
        assertTrue(actualCustomerById.getHeaders().isEmpty());
    }


    @Test
    void testGetCustomerById2() {

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findById((String) any())).thenReturn(Optional.of(customer));
        ResponseEntity<?> actualCustomerById = (new CustomerController(new CustomerServiceImpl(customerRepository)))
                .getCustomerById("42");
        assertTrue(actualCustomerById.hasBody());
        assertTrue(actualCustomerById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCustomerById.getStatusCode());
        verify(customerRepository).findById((String) any());
    }


    @Test
    void testGetCustomerById3() {

        ResponseEntity<?> actualCustomerById = (new CustomerController(null)).getCustomerById("42");
        assertNull(actualCustomerById.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualCustomerById.getStatusCode());
        assertTrue(actualCustomerById.getHeaders().isEmpty());
    }


    @Test
    void testGetCustomerById4() throws CustomerNotFoundException {

        CustomerService customerService = mock(CustomerService.class);
        when(customerService.getCustomerById((String) any()))
                .thenThrow(new CustomerNotFoundException("An error occurred"));
        ResponseEntity<?> actualCustomerById = (new CustomerController(customerService)).getCustomerById("42");
        assertEquals("An error occurred", actualCustomerById.getBody());
        assertEquals(HttpStatus.CONFLICT, actualCustomerById.getStatusCode());
        assertTrue(actualCustomerById.getHeaders().isEmpty());
        verify(customerService).getCustomerById((String) any());
    }


    @Test
    void testUpdateCustomer() {

        CustomerController customerController = new CustomerController(new CustomerServiceImpl());

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualUpdateCustomerResult = customerController.updateCustomer("42", customer);
        assertNull(actualUpdateCustomerResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUpdateCustomerResult.getStatusCode());
        assertTrue(actualUpdateCustomerResult.getHeaders().isEmpty());
    }


    @Test
    void testUpdateCustomer2() {

        CustomerController customerController = new CustomerController(null);

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualUpdateCustomerResult = customerController.updateCustomer("42", customer);
        assertNull(actualUpdateCustomerResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUpdateCustomerResult.getStatusCode());
        assertTrue(actualUpdateCustomerResult.getHeaders().isEmpty());
    }


    @Test
    void testUpdateCustomer3() {

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer1 = new Customer();
        customer1.setAddress("42 Main St");
        customer1.setDOB("D OB");
        customer1.setEmail("jane.doe@example.org");
        customer1.setGender("Gender");
        customer1.setMobile("Mobile");
        customer1.setName("Name");
        customer1.setNationality("Nationality");
        customer1.setParentName("Parent Name");
        customer1.setRelativeAadharNumber("42");
        customer1.setTypeOfRelation("Type Of Relation");
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        when(customerRepository.findById((String) any())).thenReturn(ofResult);
        CustomerController customerController = new CustomerController(new CustomerServiceImpl(customerRepository));

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setDOB("D OB");
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("Gender");
        customer2.setMobile("Mobile");
        customer2.setName("Name");
        customer2.setNationality("Nationality");
        customer2.setParentName("Parent Name");
        customer2.setRelativeAadharNumber("42");
        customer2.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualUpdateCustomerResult = customerController.updateCustomer("42", customer2);
        assertEquals("Customer Details are updated", actualUpdateCustomerResult.getBody());
        assertEquals(HttpStatus.CREATED, actualUpdateCustomerResult.getStatusCode());
        assertTrue(actualUpdateCustomerResult.getHeaders().isEmpty());
        verify(customerRepository).save((Customer) any());
        verify(customerRepository).findById((String) any());
    }


    @Test
    void testUpdateCustomer4() throws CustomerNotFoundException {

        CustomerService customerService = mock(CustomerService.class);
        when(customerService.updateCustomerById((String) any(), (Customer) any()))
                .thenThrow(new CustomerNotFoundException("An error occurred"));
        CustomerController customerController = new CustomerController(customerService);

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setDOB("D OB");
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setMobile("Mobile");
        customer.setName("Name");
        customer.setNationality("Nationality");
        customer.setParentName("Parent Name");
        customer.setRelativeAadharNumber("42");
        customer.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualUpdateCustomerResult = customerController.updateCustomer("42", customer);
        assertEquals("An error occurred", actualUpdateCustomerResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualUpdateCustomerResult.getStatusCode());
        assertTrue(actualUpdateCustomerResult.getHeaders().isEmpty());
        verify(customerService).updateCustomerById((String) any(), (Customer) any());
    }



    @Test
    void testDeleteCustomerById() {

        ResponseEntity<?> actualDeleteCustomerByIdResult = (new CustomerController(new CustomerServiceImpl()))
                .deleteCustomerById("42");
        assertNull(actualDeleteCustomerByIdResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualDeleteCustomerByIdResult.getStatusCode());
        assertTrue(actualDeleteCustomerByIdResult.getHeaders().isEmpty());
    }


    @Test
    void testDeleteCustomerById2() {

        CustomerRepository customerRepository = mock(CustomerRepository.class);
        doNothing().when(customerRepository).deleteById((String) any());
        ResponseEntity<?> actualDeleteCustomerByIdResult = (new CustomerController(
                new CustomerServiceImpl(customerRepository))).deleteCustomerById("42");
        assertEquals("Customer Details deleted from the database with Email Id: 42",
                actualDeleteCustomerByIdResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteCustomerByIdResult.getStatusCode());
        assertTrue(actualDeleteCustomerByIdResult.getHeaders().isEmpty());
        verify(customerRepository).deleteById((String) any());
    }


    @Test
    void testDeleteCustomerById3() {

        ResponseEntity<?> actualDeleteCustomerByIdResult = (new CustomerController(null)).deleteCustomerById("42");
        assertNull(actualDeleteCustomerByIdResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualDeleteCustomerByIdResult.getStatusCode());
        assertTrue(actualDeleteCustomerByIdResult.getHeaders().isEmpty());
    }


    @Test
    void testDeleteCustomerById4() throws CustomerNotFoundException {

        CustomerService customerService = mock(CustomerService.class);
        when(customerService.deleteCustomerById((String) any()))
                .thenThrow(new CustomerNotFoundException("An error occurred"));
        ResponseEntity<?> actualDeleteCustomerByIdResult = (new CustomerController(customerService))
                .deleteCustomerById("42");
        assertEquals("An error occurred", actualDeleteCustomerByIdResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualDeleteCustomerByIdResult.getStatusCode());
        assertTrue(actualDeleteCustomerByIdResult.getHeaders().isEmpty());
        verify(customerService).deleteCustomerById((String) any());
    }
}

