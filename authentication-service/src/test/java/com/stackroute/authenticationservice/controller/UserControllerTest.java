package com.stackroute.authenticationservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.service.UserService;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;


    @Test
    void testRegisterNewUser() throws Exception {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("ayaz@gmail.com");
        user.setUserPassword("Ayaz@1234");
        when(userService.registerNewUser((String) any(), (String) any())).thenReturn(user);
        doNothing().when(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/registerNewUser")
                .param("userName", "ayaz@gmail.com")
                .param("userPassword", "Ayaz@1234");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"userName\":\"ayaz@gmail.com\",\"userPassword\":\"Ayaz@1234\",\"role\":[]}"));
    }


    @Test
    void testForOperator() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/operator");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("welcome in Operator board"));
    }


    @Test
    void testRegiserNewOperator() throws Exception {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("ayaz@gmail.com");
        user.setUserPassword("Ayaz@1234");
        when(userService.registerNewOperator((String) any(), (String) any())).thenReturn(user);
        doNothing().when(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/registerNewOperator")
                .param("userName", "ayaz@gmail.com")
                .param("userPassword", "Ayaz@1234");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"userName\":\"ayaz@gmail.com\",\"userPassword\":\"Ayaz@1234\",\"role\":[]}"));
    }



    @Test
    void testForUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("welcome in user board"));
    }


}

