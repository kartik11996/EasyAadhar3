package com.stackroute.operatorservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.context.properties.bind.DefaultValue;

public enum AppointmentStatus {
        @JsonProperty("available")
        Available,
        @JsonProperty("booked")
        Booked;
    }
