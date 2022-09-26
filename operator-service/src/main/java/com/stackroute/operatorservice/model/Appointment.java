package com.stackroute.operatorservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    private String appointmentId;
    @JsonFormat(pattern = "HH:mm:ss")
    private String appointmentStartTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private String appointmentEndTime;
    private AppointmentStatus appointmentStatus;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String appointmentDate;


}
