package com.stackroute.operatorservice.model;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
@Data
public class AadharCenterRegister {
    private String centerId;
    private String centerName;
    private String openingTime;
    private String closingTime;
    private List<String> amenities;
    private String address;
    private String city;
    private String state;
    private long locationPin;
    private BufferedImage visualsOfCenter;
    private String centerDescription;
    private String contactInfo;
    private List<String>placesNearBy;
    private List<String> transportFacilities;
    private Date postedDate;

}
