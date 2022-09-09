package com.stackroute.slotbookingservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
@Entity
@Data
public class SlotBookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String AppointmentId;
    private Date AppointmentDate;
    private String AppointmentTime;
    private String ApplicationId;
    private String Name;
    private String CenterName;
    private String Mobile;

}
