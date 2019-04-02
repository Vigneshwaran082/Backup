package com.app.sugarcrush.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.sugarcrush.model.Order;
import com.app.sugarcrush.model.User;


/*
 * R & D interface 
 * */
@Repository
public interface UserDao  {

	 public List<User> getAllUsers(int offSet , int max);
	 
	 public User persistUser(User user);
	 
	 public User getUserById(long userId);
	 
	 public void deleteUser(User user);
	 
	 public void deleteUserById(long userId);
	 
	 public List<Order> getAllOrdersForAUser(long userId);
}
