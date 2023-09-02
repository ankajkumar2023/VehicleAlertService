package com.ank.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ank.literals.VehicleAlertLiterals;
import com.ank.model.ExceptionResponse;
import com.google.zxing.WriterException;


@ControllerAdvice
public class VehicleAlertExceptionController {
	
	private final Log logger = LogFactory.getLog(VehicleAlertExceptionController.class);
	
	@Autowired
    AuthenticationEntryPoint authEntryPoint;
	
	
	@ExceptionHandler({ InsufficientAuthenticationException.class })
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(InsufficientAuthenticationException ex) {
		logger.debug(ex.getMessage());
		ExceptionResponse exceptionError = new ExceptionResponse(VehicleAlertLiterals.AUTHENTICATION_FAIL_CODE,ex.getMessage());
		exceptionError.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(exceptionError , HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler(value = { VehicleAlertException.class})
	public ResponseEntity<Object> customException(VehicleAlertException exception) {
		
		ExceptionResponse exceptionError = new ExceptionResponse(exception.getErrorCode(),exception.getErrorMessage());
		exceptionError.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(exceptionError , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {IOException.class})
	public ResponseEntity<Object> ioException(IOException exception) {
		
		logger.debug(exception.getMessage());
		
		ExceptionResponse exceptionError =null;
	    exceptionError= new ExceptionResponse(VehicleAlertLiterals.GENERIC_CODE,exception.getMessage());
		exceptionError.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(exceptionError , HttpStatus.NOT_FOUND);
	
   }
	
	@ExceptionHandler(value = {WriterException.class})
	public ResponseEntity<Object> writeException(WriterException exception) {
		
		logger.debug(exception.getMessage());
		ExceptionResponse exceptionError =null;
	    exceptionError= new ExceptionResponse(VehicleAlertLiterals.GENERIC_CODE,exception.getMessage());
		exceptionError.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(exceptionError , HttpStatus.NOT_FOUND);
	
   }
	
	
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	public ResponseEntity<Object> dbException(DataIntegrityViolationException exception) {
		
		logger.debug(exception.getMessage());
		ExceptionResponse exceptionError =null;
	    exceptionError= new ExceptionResponse(VehicleAlertLiterals.DB_ERROR_CODE,VehicleAlertLiterals.DB_ERROR__MSG);
		exceptionError.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(exceptionError , HttpStatus.NOT_FOUND);
	
   }
	
		
	@ExceptionHandler(value = {IllegalStateException.class})
	public ResponseEntity<Object> genericException(VehicleAlertException exception) {
		
		logger.debug(exception.getMessage());
		
		ExceptionResponse exceptionError =null;
		if(exception.getException() !=null) {
			
			exceptionError= new ExceptionResponse(VehicleAlertLiterals.GENERIC_CODE,exception.getException().getMessage());
		}	
		exceptionError.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(exceptionError , HttpStatus.NOT_FOUND);
	
   }

}
