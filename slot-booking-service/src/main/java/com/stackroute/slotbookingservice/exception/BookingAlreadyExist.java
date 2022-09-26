package com.stackroute.slotbookingservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingAlreadyExist extends Exception{
    String errorMessage;

}
