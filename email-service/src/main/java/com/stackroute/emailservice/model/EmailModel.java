package com.stackroute.emailservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor

public class EmailModel {


	@NotBlank
	@Size(max = 40)
	private	String userMail;
	@NotBlank
	@Size(max = 60)
	private	String mailBody;
	@NotBlank
	@Size(max = 100)
	private	String mailSubject;
	
}