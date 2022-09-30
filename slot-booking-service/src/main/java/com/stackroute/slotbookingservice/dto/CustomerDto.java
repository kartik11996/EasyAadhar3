package com.stackroute.slotbookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CustomerDto{



    private String email;


    private String name;


    private String mobile;





}