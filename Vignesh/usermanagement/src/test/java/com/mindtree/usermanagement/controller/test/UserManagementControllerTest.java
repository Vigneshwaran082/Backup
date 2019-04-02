package com.mindtree.usermanagement.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.usermanagement.model.User;
import com.mindtree.usermanagement.repo.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserManagementControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserRepo userRepo;
	
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void startup() {
		User user = new User();
		user.setEmailId("test1@test.com");
		user.setFirstName("test1");
		user.setLastName("test1");
		user.setMobile(81478755176l);
	   
		User user1 = new User();
		user1.setEmailId("test1@test.com");
		user1.setFirstName("test1");
		user1.setLastName("test1");
		user1.setMobile(81478755176l);
	   
		
		User user2 = new User();
		user2.setEmailId("test1@test.com");
		user2.setFirstName("test1");
		user2.setLastName("test1");
		user2.setMobile(81478755176l);
	   
		userRepo.save(user);
		userRepo.save(user1);
		userRepo.save(user2);
		
		
		user1 =user;
		user2 =user1;
		user3 =user2;
		
		
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void getAllUsers() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("/getAllUser", List.class);

		List<User> users = responseEntity.getBody();
		assertTrue( users.size() >= 3);

	}
	
	
	@Test
	//@Rollback(true) 
	public void testRegisterUser() {
		
		User user = new User();
		user.setEmailId("new_test_user@test.com");
		user.setFirstName("New_User");
		user.setLastName("Test");
		user.setMobile(81478755176l);
	   
		
		ResponseEntity<User> responseEntity = restTemplate.postForEntity("/registerUser", user, User.class);

		 user = responseEntity.getBody();
		assertNotNull(user.getUserId());

	}

	
	@Test
	public void testDeleteUser() {
		
		restTemplate.delete("/deleteUser/"+user1.getUserId());

	    User user = userRepo.findOne(user1.getUserId());
	    assertNull(user);
	    
	}
	
	
	@Test
	public void testUpdateUser() {
		
		User user = new User();
		user.setEmailId("test_1@test.com");
		user.setFirstName("test_1");
		user.setLastName("test_2");
		user.setMobile(81478755123l);
		user.setUserId(user1.getUserId());
				
		ResponseEntity<User> updateResponseEntity = restTemplate.postForEntity("/updateUser", user, User.class);

		user = updateResponseEntity.getBody();
		assertNotNull(user.getLastName().equals("test_2"));
		
	    
	    
	}
	
	
	
	
	
	
	


}
