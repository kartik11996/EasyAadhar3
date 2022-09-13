package com.stackroute.operatorservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BusinessException extends RuntimeException{
   // private static final long serialversionUID = 1L;
    private String errorCode;
    private String errorMessage;
}
