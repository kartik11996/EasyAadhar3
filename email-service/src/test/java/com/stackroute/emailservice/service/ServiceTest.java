package com.stackroute.emailservice.service;

import com.stackroute.emailservice.model.EmailModel;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class ServiceTest {
    @Autowired
    JavaMailSender javaMailSender;



    public void sendSimpleMail(EmailModel details) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        javaMailSender.send(mailMessage);

    }


}