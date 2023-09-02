package com.ank.exception;

public class VehicleAlertException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	private Integer errorCode;
	
	private String errorMessage;
	
	private Exception exception;
		
	public VehicleAlertException(Exception exception) {
		super();
		this.exception = exception;
	}

	
	public VehicleAlertException(Integer errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	

	public VehicleAlertException(Integer errorCode, String errorMessage, Exception exception) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.exception = exception;
	}


	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Exception getException() {
		return exception;
	}


	@Override
	public String toString() {
		return "VehicleAlertException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", exception="
				+ exception + "]";
	}
	
	
		
}
