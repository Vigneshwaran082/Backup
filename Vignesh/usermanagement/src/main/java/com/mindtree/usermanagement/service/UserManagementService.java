package com.mindtree.usermanagement.service;

import java.util.List;

import com.mindtree.usermanagement.model.User;

public interface UserManagementService {

	
	public List<User> getAllUser();
	
	public User findById(Long userId);
	
	public User createUser(User user);
	
	public void deleteUser(Long userId);
	
	public User updateUser(User user);
	
	
}
