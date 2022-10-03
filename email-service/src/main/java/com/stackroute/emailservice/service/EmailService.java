package com.stackroute.emailservice.service;

import com.stackroute.emailservice.config.RabbitMqConfiguration;
import com.stackroute.emailservice.dto.BookingDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.stackroute.emailservice.exception.emailNotSendException;

 

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;




	@RabbitListener(queues = RabbitMqConfiguration.QUEUE1)
	public void sendEmail(BookingDto booking) {

		SimpleMailMessage msg=new SimpleMailMessage();

		msg.setTo(booking.getEmailId());
		msg.setSubject("Aadhar Slot booking details");
		msg.setText(booking.toString());
		try {
			sender.send(msg);
			System.out.println("slot booked for -->  "+booking);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("message not send !!! ");
		}

	}




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
			e.printStackTrace();
			throw new emailNotSendException("mail not send or wrong email id");
		}
		 
	}

}
