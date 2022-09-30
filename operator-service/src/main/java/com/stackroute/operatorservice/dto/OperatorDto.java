package com.stackroute.operatorservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.stackroute.operatorservice.model.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class OperatorDto{




    private String centerName;

  //  @JsonFormat(pattern="dd/MM/yyyy")
    private String appointmentDate;

    private String appointmentTime;

    private String centerAddress;
}