package com.stackroute.operatorservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.stackroute.operatorservice.exception.BusinessException;
import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.service.AadharCenterService;

import java.util.ArrayList;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {AadharCenterController.class})
@ExtendWith(SpringExtension.class)
class AadharCenterControllerTest {
    @Autowired
    private AadharCenterController aadharCenterController;

    @MockBean
    private AadharCenterService aadharCenterService;

    @Test
    void testCreate() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.post("/aadharcenter/saveaadharcenter")
                .param("aadharcenter", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("file", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetCenterByCity() throws Exception {
        when(aadharCenterService.getCenterByCity((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getcenterbycity/{city}",
                "Oxford");
        MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetCenterByCity2() throws Exception {
        when(aadharCenterService.getCenterByCity((String) any()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getcenterbycity/{city}",
                "Oxford");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("Error Code: An error occurred\nError Message:An error occurred"));
    }

    @Test
    void testGetCenterByCity3() throws Exception {
        when(aadharCenterService.getCenterByCity((String) any())).thenThrow(new NoSuchElementException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getcenterbycity/{city}",
                "Oxford");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Error Code: 615\nError Message: Something went wrong in controller"));
    }

    @Test
    void testGetCenterByCity4() throws Exception {
        when(aadharCenterService.getCenterByCity((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/aadharcenter/getcenterbycity/{city}",
                "Oxford");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetCenterById() throws Exception {
        when(aadharCenterService.getCenterById((String) any())).thenReturn(new AadharCenterRegister());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getcenterbyid/{id}",
                "42");
        MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"centerId\":null,\"centerName\":null,\"openingTime\":null,\"closingTime\":null,\"amenities\":null,\"address\""
                                        + ":null,\"city\":null,\"state\":null,\"locationPin\":0,\"visualsOfCenter\":null,\"centerDescription\":null,"
                                        + "\"contactInfo\":null,\"placesNearBy\":null,\"transportFacilities\":null,\"postedDate\":null}"));
    }

    @Test
    void testGetCenterById2() throws Exception {
        when(aadharCenterService.getCenterById((String) any()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getcenterbyid/{id}",
                "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("Error Code: An error occurred\nError Message:An error occurred"));
    }

    @Test
    void testGetCenterById3() throws Exception {
        when(aadharCenterService.getCenterById((String) any())).thenThrow(new NoSuchElementException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getcenterbyid/{id}",
                "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("StatusCode: 616,\nError Message:Something went wrong in controller"));
    }

    @Test
    void testDeleteCenterById() throws Exception {
        when(aadharCenterService.deleteById((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/aadharcenter/deleteaadharcenter/{id}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void testDeleteCenterById2() throws Exception {
        when(aadharCenterService.deleteById((String) any()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/aadharcenter/deleteaadharcenter/{id}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("Error Code: An error occurred\nError Message:An error occurred"));
    }

    @Test
    void testDeleteCenterById3() throws Exception {
        when(aadharCenterService.deleteById((String) any())).thenThrow(new NoSuchElementException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/aadharcenter/deleteaadharcenter/{id}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Error Code: 614\nError Message: Something went wrong in controller"));
    }

    @Test
    void testDeleteCenterById4() throws Exception {
        when(aadharCenterService.deleteById((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders
                .delete("/aadharcenter/deleteaadharcenter/{id}", "42");
        deleteResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void testFindAllCenter() throws Exception {
        when(aadharCenterService.getAllCenter()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getallcenters");
        MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFindAllCenter2() throws Exception {
        when(aadharCenterService.getAllCenter())
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getallcenters");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("Error Code: An error occurred\nError Message:An error occurred"));
    }

    @Test
    void testFindAllCenter3() throws Exception {
        when(aadharCenterService.getAllCenter()).thenThrow(new NoSuchElementException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aadharcenter/getallcenters");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Error Code: 612\nError Message: Something went wrong in controller"));
    }

    @Test
    void testFindAllCenter4() throws Exception {
        when(aadharCenterService.getAllCenter()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/aadharcenter/getallcenters");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFindByPinCode() throws Exception {
        when(aadharCenterService.getCenterByLocationPin(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/aadharcenter/getcenterbylocationpin/{locationPin}", 1L);
        MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindByPinCode2() throws Exception {
        when(aadharCenterService.getCenterByLocationPin(anyLong()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/aadharcenter/getcenterbylocationpin/{locationPin}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("Error Code: An error occurred\nError Message:An error occurred"));
    }

    @Test
    void testFindByPinCode3() throws Exception {
        when(aadharCenterService.getCenterByLocationPin(anyLong())).thenThrow(new NoSuchElementException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/aadharcenter/getcenterbylocationpin/{locationPin}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Error Code: 617\nError Message: Something went wrong in controller"));
    }

    @Test
    void testFindByPinCode4() throws Exception {
        when(aadharCenterService.getCenterByLocationPin(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/aadharcenter/getcenterbylocationpin/{locationPin}", 1L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdate() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders
                .put("/aadharcenter/updateaadharcenter/{id}", "42")
                .param("aadharcenter", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("file", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aadharCenterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

