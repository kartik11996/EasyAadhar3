package com.stackroute.slotbookingservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentId;

	private String emailId;
	//@JsonFormat(pattern="dd/MM/yyyy")
    private String appointmentDate;
    private String appointmentTime;
    private String customername;
    private String centerName;
    private String mobile;
    private String address;
    
}
