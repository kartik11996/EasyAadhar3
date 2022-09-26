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
import com.stackroute.customerservice.model.CustomerList;
import com.stackroute.customerservice.repository.CustomerRepository;
import com.stackroute.customerservice.service.CustomerService;
import com.stackroute.customerservice.service.CustomerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CustomerControllerTest {

    @Test
    void testAddCustomer() {
        CustomerController customerController = new CustomerController(new CustomerServiceImpl());

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualAddCustomerResult = customerController.addCustomer(customerList);
        assertNull(actualAddCustomerResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualAddCustomerResult.getStatusCode());
        assertTrue(actualAddCustomerResult.getHeaders().isEmpty());
    }


    @Test
    void testAddCustomer2() {

        CustomerController customerController = new CustomerController(null);

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualAddCustomerResult = customerController.addCustomer(customerList);
        assertNull(actualAddCustomerResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualAddCustomerResult.getStatusCode());
        assertTrue(actualAddCustomerResult.getHeaders().isEmpty());
    }


    @Test
    void testAddCustomer3() {

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.save((CustomerList) any())).thenReturn(customerList);
        CustomerController customerController = new CustomerController(new CustomerServiceImpl(customerRepository));

        CustomerList customerList1 = new CustomerList();
        customerList1.setAddress("42 Main St");
        customerList1.setDOB("D OB");
        customerList1.setEmail("jane.doe@example.org");
        customerList1.setGender("Gender");
        customerList1.setMobile("Mobile");
        customerList1.setName("Name");
        customerList1.setNationality("Nationality");
        customerList1.setParentName("Parent Name");
        customerList1.setRelativeAadharNumber("42");
        customerList1.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualAddCustomerResult = customerController.addCustomer(customerList1);
        assertEquals("Customer has been added successfully", actualAddCustomerResult.getBody());
        assertEquals(HttpStatus.CREATED, actualAddCustomerResult.getStatusCode());
        assertTrue(actualAddCustomerResult.getHeaders().isEmpty());
        verify(customerRepository).save((CustomerList) any());
    }


    @Test
    void testAddCustomer4() throws CustomerAlreadyExistsException {

        CustomerService customerService = mock(CustomerService.class);
        when(customerService.saveCustomer((CustomerList) any()))
                .thenThrow(new CustomerAlreadyExistsException("An error occurred"));
        CustomerController customerController = new CustomerController(customerService);

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualAddCustomerResult = customerController.addCustomer(customerList);
        assertEquals("An error occurred", actualAddCustomerResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualAddCustomerResult.getStatusCode());
        assertTrue(actualAddCustomerResult.getHeaders().isEmpty());
        verify(customerService).saveCustomer((CustomerList) any());
    }




    @Test
    void testGetAllCustomers() {

        CustomerRepository customerRepository = mock(CustomerRepository.class);
        ArrayList<CustomerList> customerListList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerListList);
        List<CustomerList> actualAllCustomers = (new CustomerController(new CustomerServiceImpl(customerRepository)))
                .getAllCustomers();
        assertSame(customerListList, actualAllCustomers);
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

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findById((String) any())).thenReturn(Optional.of(customerList));
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

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualUpdateCustomerResult = customerController.updateCustomer("42", customerList);
        assertNull(actualUpdateCustomerResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUpdateCustomerResult.getStatusCode());
        assertTrue(actualUpdateCustomerResult.getHeaders().isEmpty());
    }


    @Test
    void testUpdateCustomer2() {

        CustomerController customerController = new CustomerController(null);

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualUpdateCustomerResult = customerController.updateCustomer("42", customerList);
        assertNull(actualUpdateCustomerResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUpdateCustomerResult.getStatusCode());
        assertTrue(actualUpdateCustomerResult.getHeaders().isEmpty());
    }


    @Test
    void testUpdateCustomer3() {

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        Optional<CustomerList> ofResult = Optional.of(customerList);

        CustomerList customerList1 = new CustomerList();
        customerList1.setAddress("42 Main St");
        customerList1.setDOB("D OB");
        customerList1.setEmail("jane.doe@example.org");
        customerList1.setGender("Gender");
        customerList1.setMobile("Mobile");
        customerList1.setName("Name");
        customerList1.setNationality("Nationality");
        customerList1.setParentName("Parent Name");
        customerList1.setRelativeAadharNumber("42");
        customerList1.setTypeOfRelation("Type Of Relation");
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.save((CustomerList) any())).thenReturn(customerList1);
        when(customerRepository.findById((String) any())).thenReturn(ofResult);
        CustomerController customerController = new CustomerController(new CustomerServiceImpl(customerRepository));

        CustomerList customerList2 = new CustomerList();
        customerList2.setAddress("42 Main St");
        customerList2.setDOB("D OB");
        customerList2.setEmail("jane.doe@example.org");
        customerList2.setGender("Gender");
        customerList2.setMobile("Mobile");
        customerList2.setName("Name");
        customerList2.setNationality("Nationality");
        customerList2.setParentName("Parent Name");
        customerList2.setRelativeAadharNumber("42");
        customerList2.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualUpdateCustomerResult = customerController.updateCustomer("42", customerList2);
        assertEquals("Customer Details are updated", actualUpdateCustomerResult.getBody());
        assertEquals(HttpStatus.CREATED, actualUpdateCustomerResult.getStatusCode());
        assertTrue(actualUpdateCustomerResult.getHeaders().isEmpty());
        verify(customerRepository).save((CustomerList) any());
        verify(customerRepository).findById((String) any());
    }


    @Test
    void testUpdateCustomer4() throws CustomerNotFoundException {

        CustomerService customerService = mock(CustomerService.class);
        when(customerService.updateCustomerById((String) any(), (CustomerList) any()))
                .thenThrow(new CustomerNotFoundException("An error occurred"));
        CustomerController customerController = new CustomerController(customerService);

        CustomerList customerList = new CustomerList();
        customerList.setAddress("42 Main St");
        customerList.setDOB("D OB");
        customerList.setEmail("jane.doe@example.org");
        customerList.setGender("Gender");
        customerList.setMobile("Mobile");
        customerList.setName("Name");
        customerList.setNationality("Nationality");
        customerList.setParentName("Parent Name");
        customerList.setRelativeAadharNumber("42");
        customerList.setTypeOfRelation("Type Of Relation");
        ResponseEntity<?> actualUpdateCustomerResult = customerController.updateCustomer("42", customerList);
        assertEquals("An error occurred", actualUpdateCustomerResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualUpdateCustomerResult.getStatusCode());
        assertTrue(actualUpdateCustomerResult.getHeaders().isEmpty());
        verify(customerService).updateCustomerById((String) any(), (CustomerList) any());
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

