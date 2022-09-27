package com.stackroute.customerservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.customerservice.exception.CustomerAlreadyExistsException;
import com.stackroute.customerservice.exception.CustomerNotFoundException;
import com.stackroute.customerservice.model.Customer;
import com.stackroute.customerservice.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    /**
     * Method under test: {@link CustomerServiceImpl#getAllCustomers()}
     */
    @Test
    void testGetAllCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> actualAllCustomers = customerServiceImpl.getAllCustomers();
        assertSame(customerList, actualAllCustomers);
        assertTrue(actualAllCustomers.isEmpty());
        verify(customerRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#saveCustomer(Customer)}
     */
    @Test
    void testSaveCustomer() throws CustomerAlreadyExistsException {
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
        when(customerRepository.save((Customer) any())).thenReturn(customer);

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
        assertEquals("Customer has been added successfully", customerServiceImpl.saveCustomer(customer1));
        verify(customerRepository).save((Customer) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#updateCustomerById(String, Customer)}
     */
    @Test
    void testUpdateCustomerById() throws CustomerNotFoundException {
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
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        when(customerRepository.findById((String) any())).thenReturn(ofResult);

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
        assertEquals("Customer Details are updated", customerServiceImpl.updateCustomerById("42", customer2));
        verify(customerRepository).save((Customer) any());
        verify(customerRepository).findById((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#updateCustomerById(String, Customer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCustomerById2() throws CustomerNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.stackroute.customerservice.service.CustomerServiceImpl.updateCustomerById(CustomerServiceImpl.java:45)
        //   In order to prevent updateCustomerById(String, CustomerList)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateCustomerById(String, CustomerList).
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(customerRepository.findById((String) any())).thenReturn(Optional.empty());

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
        customerServiceImpl.updateCustomerById("42", customer1);
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getCustomerById(String)}
     */
    @Test
    void testGetCustomerById() throws CustomerNotFoundException {
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
        when(customerRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(customer, customerServiceImpl.getCustomerById("42"));
        verify(customerRepository).findById((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getCustomerById(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCustomerById2() throws CustomerNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.stackroute.customerservice.service.CustomerServiceImpl.getCustomerById(CustomerServiceImpl.java:70)
        //   In order to prevent getCustomerById(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getCustomerById(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findById((String) any())).thenReturn(Optional.empty());
        customerServiceImpl.getCustomerById("42");
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getCustomerByMobile(String)}
     */
    @Test
    void testGetCustomerByMobile() throws CustomerNotFoundException {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findByMobile((String) any())).thenReturn(customerList);
        List<Customer> actualCustomerByMobile = customerServiceImpl.getCustomerByMobile("Mobile");
        assertSame(customerList, actualCustomerByMobile);
        assertTrue(actualCustomerByMobile.isEmpty());
        verify(customerRepository).findByMobile((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#deleteCustomerById(String)}
     */
    @Test
    void testDeleteCustomerById() throws CustomerNotFoundException {
        doNothing().when(customerRepository).deleteById((String) any());
        assertTrue(customerServiceImpl.deleteCustomerById("42"));
        verify(customerRepository).deleteById((String) any());
    }
}

