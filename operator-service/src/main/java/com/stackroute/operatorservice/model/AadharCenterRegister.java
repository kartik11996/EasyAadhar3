package com.stackroute.operatorservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "aadharcenter")
public class AadharCenterRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String centerId;
    private String centerName;
    private String openingTime;
    private String closingTime;
    private List<String> amenities;
    private String address;
    private String city;
    private String state;
    private long locationPin;
    private byte[] visualsOfCenter;
    private String centerDescription;
    private String contactInfo;
    private List<String>placesNearBy;
    private List<String> transportFacilities;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date postedDate;
}

