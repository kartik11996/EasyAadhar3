package com.stackroute.emailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.stackroute.emailservice.exception.emailNotSendException;

 

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	
	 
	public String sendSimpleMessage(String userMail, String subject, String body) throws emailNotSendException  {
		// TODO Auto-generated method stub

		SimpleMailMessage msg=new SimpleMailMessage();

		msg.setTo(userMail);
		msg.setSubject(subject);
		msg.setText(body);
		try {
		sender.send(msg);
		
		return "Mail Send Successfully";
		}catch(Exception e) {
			
			throw new emailNotSendException("mail not send or wrong email id");
		}
		 
	}

}
