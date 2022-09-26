package com.stackroute.customerservice.model;

import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "CustomerDetails")
@EnableAutoConfiguration
public class CustomerList {

    @NonNull
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
//take enum
    @NonNull
    private String relativeAadharNumber;


}
