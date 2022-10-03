package com.stackroute.authenticationservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authenticationservice.exception.InvalidCredentialException;
import com.stackroute.authenticationservice.model.JwtRequest;
import com.stackroute.authenticationservice.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {JwtController.class})
@ExtendWith(SpringExtension.class)
class JwtControllerTest {
    @Autowired
    private JwtController jwtController;

    @MockBean
    private JwtService jwtService;



    @Test
    void testCreateJwtToken() throws Exception {
        when((ResponseEntity<Object>) jwtService.createJwtToken((JwtRequest) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("ayaz@gmail.com");
        jwtRequest.setUserPassword("Ayaz@1234");
        String content = (new ObjectMapper()).writeValueAsString(jwtRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jwtController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"headers\":{},\"body\":null,\"statusCode\":\"CONTINUE\",\"statusCodeValue\":100}"));
    }


}

