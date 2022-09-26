package com.stackroute.slotbookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@EnableAutoConfiguration
public class Customer{

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
