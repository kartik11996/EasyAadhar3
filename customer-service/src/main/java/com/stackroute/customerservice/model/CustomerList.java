package com.stackroute.customerservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "CustomerList") 
@Data
public class CustomerList {

    private String name;
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
