package com.lab.lab.Util.exceptions;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleException(Exception ex) {
        return new ResponseEntity<ErrorMessage>( new ErrorMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadCredentialsException.class) // when Invalid Credentials
    public ResponseEntity<ErrorMessage> handleInvalidCredentialsException(
            BadCredentialsException e) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
