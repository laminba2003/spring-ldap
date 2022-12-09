package com.spring.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.NamingException;
import java.time.LocalDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {NamingException.class})
    public ResponseEntity<APIException> handleEntityNotFoundException(NamingException e) {
        APIException exception = new APIException(e.getMessage(),
                HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        APIException exception = new APIException("the input provided is invalid",
                HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

}