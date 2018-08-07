package com.digitalbank.shan.transactionserver.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.digitalbank.shan.transactionserver.exception.InvalidInputException;

@RestControllerAdvice
public class TransactionControllerAdvice {

	@ExceptionHandler(InvalidInputException.class)
	public final ResponseEntity<String> handleInvalidInputException(InvalidInputException e) {
	    return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleAllException(Exception e) {
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
