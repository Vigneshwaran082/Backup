package com.app.sugarcrush.exception;

public class UserNotFoundException  extends Exception{

	private static final long serialVersionUID = 1L;

	private String message ="USER_NOT_FOUND";
	
	private Long userId;
	
	public UserNotFoundException() {
	}
	
	public UserNotFoundException(String message) {
		this.message = message;
	}
	
	public UserNotFoundException(Long userId) {
		this.userId = userId;
	}
	
	public UserNotFoundException(String message, Long userId) {
		this.message = message;
		this.userId = userId;
	}

	public String getDetailedMessage() {
		String humanReadableMessage =  message;
		if(userId !=null && message.equals("USER_NOT_FOUND")) {
			humanReadableMessage = message + " with user-Id :" + userId;
		}else if(userId == null && message.equals("USER_NOT_FOUND")) {
			humanReadableMessage = "USER_IS_NOT_PERSISTED_IN_DATABASE";
		}
		return humanReadableMessage;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
