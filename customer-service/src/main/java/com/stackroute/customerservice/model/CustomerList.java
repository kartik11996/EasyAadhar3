package com.stackroute.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "CustomerDetails")
//@EnableAutoConfiguration
public class CustomerList {

	private String name;
	@Id 
    private String email;

    @NonNull
    private String mobile;

    @NonNull
    private String address;

    @NonNull
    private String nationality;

    @NonNull
    private String gender;

    @NonNull
    private String dOB;

    @NonNull
    private String parentName;

    @NonNull
    private String typeOfRelation;

    @NonNull
    private String relativeAadharNumber;


}
