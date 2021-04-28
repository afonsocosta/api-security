package br.com.santasecret.security.exception;

import org.springframework.http.HttpStatus;

public class BadCredentialsException extends SecurityException {

    public BadCredentialsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
    
}
