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
import com.stackroute.customerservice.model.CustomerList;
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
        ArrayList<CustomerList> customerListList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerListList);
        List<CustomerList> actualAllCustomers = customerServiceImpl.getAllCustomers();
        assertSame(customerListList, actualAllCustomers);
        assertTrue(actualAllCustomers.isEmpty());
        verify(customerRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#saveCustomer(CustomerList)}
     */
    @Test
    void testSaveCustomer() throws CustomerAlreadyExistsException {
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
        when(customerRepository.save((CustomerList) any())).thenReturn(customerList);

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
        assertEquals("Customer has been added successfully", customerServiceImpl.saveCustomer(customerList1));
        verify(customerRepository).save((CustomerList) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#updateCustomerById(String, CustomerList)}
     */
    @Test
    void testUpdateCustomerById() throws CustomerNotFoundException {
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
        when(customerRepository.save((CustomerList) any())).thenReturn(customerList1);
        when(customerRepository.findById((String) any())).thenReturn(ofResult);

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
        assertEquals("Customer Details are updated", customerServiceImpl.updateCustomerById("42", customerList2));
        verify(customerRepository).save((CustomerList) any());
        verify(customerRepository).findById((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#updateCustomerById(String, CustomerList)}
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
        when(customerRepository.save((CustomerList) any())).thenReturn(customerList);
        when(customerRepository.findById((String) any())).thenReturn(Optional.empty());

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
        customerServiceImpl.updateCustomerById("42", customerList1);
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getCustomerById(String)}
     */
    @Test
    void testGetCustomerById() throws CustomerNotFoundException {
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
        when(customerRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(customerList, customerServiceImpl.getCustomerById("42"));
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
        ArrayList<CustomerList> customerListList = new ArrayList<>();
        when(customerRepository.findByMobile((String) any())).thenReturn(customerListList);
        List<CustomerList> actualCustomerByMobile = customerServiceImpl.getCustomerByMobile("Mobile");
        assertSame(customerListList, actualCustomerByMobile);
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

