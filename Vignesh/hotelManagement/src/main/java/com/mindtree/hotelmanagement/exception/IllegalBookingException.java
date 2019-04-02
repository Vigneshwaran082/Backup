package com.mindtree.hotelmanagement.exception;

public class IllegalBookingException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	
	public IllegalBookingException(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
