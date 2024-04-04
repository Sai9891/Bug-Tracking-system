package com.bugapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleBookingNotFoundException(ResourceNotFoundException bnfe){
		return new ResponseEntity<String>(bnfe.getLocalizedMessage(),HttpStatus.NOT_FOUND);
	}
}