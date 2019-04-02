package com.mindtree.hotelmanagement.exception;

public class NoRoomAvailableException extends Exception{

	private static final long serialVersionUID = 1L;
	public String message;
	
	public NoRoomAvailableException(String message) {
		this.message = message;
	}
	
}
