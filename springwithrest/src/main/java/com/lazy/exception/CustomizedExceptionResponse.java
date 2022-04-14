package com.lazy.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// this class is user for custom exception for all controlles 
@ControllerAdvice
@RestController

public class CustomizedExceptionResponse extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handelAllException(Exception ex ,WebRequest request){
		
		ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(),
				ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity(exceptionresponse , HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserNotFound.class)
	public final ResponseEntity<Object> userNotFoundException(UserNotFound ex ,WebRequest request){
		
		ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(),
				ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity(exceptionresponse , HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(),
				"Field Validation Failed", ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionresponse , HttpStatus.BAD_REQUEST);
	}

}
