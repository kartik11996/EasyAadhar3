package com.stackroute.customerservice.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "CustomerDetails") 
@Data
//@EnableAutoConfiguration
public class CustomerList {

	private String name;
	@Id 
    private String email;
    private String mobile;
    private String address;
    private String nationality;
    private String gender;
    private String dOB;
    private String parentName;
    private String typeOfRelation;
    private String relativeAadharNumber;

}
