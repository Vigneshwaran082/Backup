package com.app.sugarcrush.service;

import java.util.List;

import com.app.sugarcrush.model.User;

public interface UserService {

	public List<User> getAllGuestUsers(int offSet, int max);
	
	public List<User> getAllNonGuestUsers(int offSet, int max);
	
	public List<User> getAllUsers(int offSet, int max);
	
	public User getUserbyId(long userId);
	
	public User persistUser(User user);
	
	public User updateUser(User user);
	
	public User deleteUser(long userId);
	
	public User deletUser(User user);
	
	
	
}
