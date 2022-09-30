package com.stackroute.paymentservice.dto;


import lombok.*;

@Data
@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private int appointmentId;

    private String emailId;
    private String appointmentDate;
    private String appointmentTime;
    private String customername;
    private String centerName;
    private String mobile;
    private String address;
}
