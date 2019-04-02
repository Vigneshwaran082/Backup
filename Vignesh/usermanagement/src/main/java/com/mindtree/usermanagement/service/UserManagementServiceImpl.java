package com.mindtree.usermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.usermanagement.model.User;
import com.mindtree.usermanagement.repo.UserRepo;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	
	@Autowired
	UserRepo userRepo;

	public List<User> getAllUser() {

		Iterable<User> users = userRepo.findAll();
		List<User> usersList = new ArrayList<User>();
		for (User user : users) {
			usersList.add(user);
		}

		return usersList;
	}

	public User findById(Long userId) {
		return userRepo.findOne(userId);
	}

	public User createUser(User user) {
		return userRepo.save(user);
	}

	public void deleteUser(Long userId) {
		userRepo.delete(userId);
	}

	public User updateUser(User user) {
		return createUser(user);
	}
	
}
