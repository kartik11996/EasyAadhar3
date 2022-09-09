package com.stackroute.customerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "CustomerList") 
@Data
@Getter
@Setter
public class CustomerList {

	private String name;
	@Id 
    private String email;
	@Id
    private String mobile;
    private String address;
    private String nationality;
    private String gender;
    private String DOB;
    private String parentName;
    private String typeOfRelation;
    private String relativeAadharNumber;
	
    
}
