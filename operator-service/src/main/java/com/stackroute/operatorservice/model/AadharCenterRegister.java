package com.stackroute.operatorservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(max = 40)
    private String centerName;
    @NotBlank
    private String openingTime;
    @NotBlank
    private String closingTime;
    private List<String> amenities;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private long locationPin;
    private byte[] visualsOfCenter;
    private String centerDescription;
    @NotBlank
    private String contactInfo;
    private List<String>placesNearBy;
    private List<String> transportFacilities;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date postedDate;
}

