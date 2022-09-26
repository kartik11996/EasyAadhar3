package com.stackroute.emailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.*;

import com.stackroute.emailservice.exception.emailNotSendException;
import com.stackroute.emailservice.service.EmailService;

@RestController
@RequestMapping("/Mail")

public class Emailcontroller {
	@Autowired
	  private EmailService service;


	@PostMapping("/send")
	public String SendEmail(@RequestParam String email,@RequestParam String subject,@RequestParam String body)throws  emailNotSendException {

		try {
	 return service.sendSimpleMessage(email, subject, body);

		}catch(emailNotSendException e) {
			
			return e.getMessage();
		}catch(Exception e) {

			return "internal server error";
		}
	 
	}

}
