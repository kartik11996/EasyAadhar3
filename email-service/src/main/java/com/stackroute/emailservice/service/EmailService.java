package com.stackroute.emailservice.service;

import com.stackroute.emailservice.exception.MailSendException;

public interface EmailService {

	String sendSimpleMessage(String string, String string2, String string3) throws MailSendException;

}
