package com.stackroute.emailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.stackroute.emailservice.exception.MailSendException;

@Component
public class EmailServiceImp implements EmailService {

	@Autowired
	private JavaMailSender sender;
	@Override
	public String sendSimpleMessage(String userMail, String subject, String body)throws MailSendException {
		// TODO Auto-generated method stub
		
		SimpleMailMessage msg=new SimpleMailMessage();
		
		msg.setTo(userMail);
		msg.setSubject(subject);
		msg.setText(body);
		sender.send(msg);
		return "Mail Send Successfully";
	}

}
