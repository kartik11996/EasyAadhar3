package com.stackroute.slotbookingservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class BookingNotFoundException  extends Exception{

    String errorMsg;

}
