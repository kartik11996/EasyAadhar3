package com.stackroute.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDto {

    private String email;

    @NonNull
    private String name;

    @NonNull
    private String mobile;

}
