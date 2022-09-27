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

//
//	@RabbitListener(queues =  RabbitMqConfiguration.QUEUE)
//	public String sendSimpleMessage1(BookingDetails bookingDetails) {
//
//
//		System.out.println(bookingDetails.toString());
////
////		SimpleMailMessage msg=new SimpleMailMessage();
////
////		msg.setTo(bookingDetails.getEmailId());
////		msg.setSubject("Booking Details ");
////		msg.setText("booking details ends");
////		sender.send(msg);
//		return "Mail Send Successfully ";
//	}
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
