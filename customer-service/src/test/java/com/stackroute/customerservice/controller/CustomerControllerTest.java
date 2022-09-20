//package com.stackroute.customerservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.mongodb.connection.Stream;
//import com.stackroute.customerservice.model.CustomerList;
//import com.stackroute.customerservice.repository.CustomerRepo;
//import com.stackroute.customerservice.service.CustomerService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(MockitoJUnitRunner.class)
////@WebMvcTest
//@SpringBootTest
//public class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    ObjectWriter objectWriter = objectMapper.writer();
////    @Mock
////    private CustomerService cs;
////
////    @Mock
////    private CustomerList cl;
//
//    @Mock
//    private CustomerRepo cr;
//
//    @InjectMocks
//    CustomerController cc;
//
//    CustomerList customer1 = new CustomerList("Ben","ben@gmail.com","9999999999","address Ben","Indian" ,"M","20/12/1880", "BenDad", "Father" ,"123412341111");
//    CustomerList customer2 = new CustomerList("David","david@gmail.com","9988999999","address David","Indian" ,"M","20/11/1880", "DavidDad", "Father" ,"123412342222");
//    CustomerList customer3 = new CustomerList("Harry","harry@gmail.com","9977999999","address Harry","Indian" ,"M","20/10/1880", "HarryDad", "Father" ,"123412343333");
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(cc).build();
//        CustomerList cl = new CustomerList();
//        cl.setName("Ben");
//        cl.setEmail("ben@gmail.com");
//        cl.setMobile("9999999999");
//        cl.setAddress("address 1234");
//        cl.setNationality("Indian");
//        cl.setGender("M");
//        cl.setDOB("20/12/1880");
//        cl.setParentName("BenDad");
//        cl.setTypeOfRelation("Father");
//        cl.setRelativeAadharNumber("123412341234");
//    }
//
//    @Test
//    public void customerAdded() throws Exception {
//
//        when(cs.saveCustomer(cl)).thenReturn(String.valueOf((cl)));
//        mockMvc.perform(post("/CustomerDetails/addCustomer")
//                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(cl)))
//                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//
//
//    }
//
//    private byte[] asJsonString(CustomerList cl) {
//        return new byte[0];
//    }
//
//
//}