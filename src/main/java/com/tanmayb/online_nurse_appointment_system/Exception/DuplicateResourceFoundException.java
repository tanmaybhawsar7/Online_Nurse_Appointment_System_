package com.tanmayb.online_nurse_appointment_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class DuplicateResourceFoundException extends RuntimeException {
    
    public DuplicateResourceFoundException(String message) {
        
        super(message);
        
    }
    
}