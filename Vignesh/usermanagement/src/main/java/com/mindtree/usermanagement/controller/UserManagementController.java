package com.mindtree.usermanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.usermanagement.model.User;
import com.mindtree.usermanagement.service.UserManagementService;

@RestController

public class UserManagementController {

	
	@Autowired
	UserManagementService userManagementService;
	
	
	
	@RequestMapping( value="/getAllUser",method=RequestMethod.GET)
	List<User> getAllUser(){
		return userManagementService.getAllUser();
	}
	
	
	@RequestMapping( value="/registerUser",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	User registerUser(@RequestBody User user){
		return userManagementService.createUser(user);
	}
	
	
	
	@RequestMapping( value="/deleteUser/{userId}",method=RequestMethod.DELETE)
	ResponseEntity<Map<String, String>> deleteUser(@PathVariable("userId") Long userId) throws Exception{
		try {
		    userManagementService.deleteUser(userId);
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
		}
		Map<String,String> responseStatus = new HashMap<String,String>();
		 responseStatus.put("status", "Deleted_Successfully");
		 ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<Map<String, String>>(responseStatus,HttpStatus.OK );
		 
	   return responseEntity;
	}
	
	
	
	@RequestMapping( value="/updateUser",method=RequestMethod.POST)
	User updateUser(@RequestBody User user){
		return userManagementService.updateUser(user);
	}
	
	
	 @ExceptionHandler(Exception.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public Map<String,String> handleError(HttpServletRequest req, Exception ex) {
		 ex.printStackTrace(System.out);
	     
	    Map<String,String> status = new HashMap<String,String>();
	    status.put("status", "Error_Occured, Please check you data and try again");
	    
	    return status;
	  }
	}
	
	
	
	

