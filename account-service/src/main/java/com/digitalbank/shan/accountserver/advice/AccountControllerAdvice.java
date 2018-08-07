package com.digitalbank.shan.accountserver.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.digitalbank.shan.accountserver.exception.InvalidInputException;

public class AccountControllerAdvice {

	@ExceptionHandler(InvalidInputException.class)
	public final ResponseEntity<String> handleInvalidInputException(InvalidInputException e) {
	    return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleAllException(Exception e) {
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}