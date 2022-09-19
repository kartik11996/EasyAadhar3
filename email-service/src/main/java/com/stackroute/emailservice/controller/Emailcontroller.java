package com.stackroute.emailservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.emailservice.exception.MailSendException;
import com.stackroute.emailservice.model.EmailModel;
import com.stackroute.emailservice.service.EmailService;
@RestController
public class Emailcontroller {
	@Autowired
	  private EmailService service;
	
	@PostMapping("/send")
	public String SendEmail(@RequestBody EmailModel m)throws MailSendException {

		return service.sendSimpleMessage(m.getUserMail(),m.getMailSubject(),m.getMailBody());

	}

}
