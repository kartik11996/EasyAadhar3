package com.authenticationservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authenticationservice.controller.UserController;
import com.stackroute.authenticationservice.model.Role;
import com.stackroute.authenticationservice.model.User;

 @RunWith(SpringRunner.class)
 @WebMvcTest(value=UserController.class)
public class UserControllerTest {

	 
	 private MockMvc mockMvc;
	 
	 ObjectMapper object=new ObjectMapper();
	 
	 @Autowired
	 private WebApplicationContext context;
	 
	 @Before
	 public void setup() {
		 
		 mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	 }
	 
	 public void registerNewUserTest() throws Exception {
		 User user=new User();
		 user.setUserName("Ayaz@gmail.com");
		 user.setUserPassword("Ayaz@pass");
		 Set<Role> role = new HashSet<>();
	     role.add(new Role("User","user role"));
		 user.setRole(role);
		 
		 String jsonRequest=object.writeValueAsString(user);
		 
		 MvcResult result=mockMvc.perform(post("/registerNewUser").
				 content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).
				 andExpect(status().isOk()).andReturn();
		 
		 String resultContext=result.getResponse().getContentAsString();
		 
		 
		 
	 }
}
