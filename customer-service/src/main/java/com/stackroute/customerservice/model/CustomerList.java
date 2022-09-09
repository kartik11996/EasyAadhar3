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

<<<<<<< HEAD
	private String name;
	@Id 
=======
    private String name;
>>>>>>> b35d50bc37931e1c03b97a637bc01fb2d049346b
    private String email;
	@Id
    private String mobile;
    private String address;
    private String nationality;
    private String gender;
    private String dOB;
    private String parentName;
    private String typeOfRelation;
    private String relativeAadharNumber;
	
    
}
