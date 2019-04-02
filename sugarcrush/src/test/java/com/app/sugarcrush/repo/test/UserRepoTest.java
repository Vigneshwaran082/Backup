package com.app.sugarcrush.repo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.app.sugarcrush.config.test.ApplicationConfig;
import com.app.sugarcrush.exception.UserNotFoundException;
import com.app.sugarcrush.model.Address;
import com.app.sugarcrush.model.User;
import com.app.sugarcrush.repo.UserRepo;




@RunWith(SpringRunner.class)
/*
 * Load's the application context meaning load all the beans which are present inside that class and if any 
 * @ComponentScan is present then scan that package
 * */
@ContextConfiguration(classes = { ApplicationConfig.class })
@ActiveProfiles("junit")
@Sql({"/user_test_script.sql","/address_test_script.sql" })
@Transactional
@Rollback
public class UserRepoTest {

	@Autowired
	UserRepo userRepo;

	
	@Test
	public void testGetAllUsers() {
		List<User> users = userRepo.findAll();
		
		assertThat(users).isNotEmpty();
		assertThat(users).size().isGreaterThanOrEqualTo(1);
		Assert.assertTrue(true);
	}

	
	
	@Test
	public void testSaveUser() {
		Address address = new Address();
		address.setAddress1("Marathahalli");
		address.setAddress2("AECS LAyout");
		address.setPinCode(560037l);
		
		User user = new User();
		user.setFirstName("Vignesh");
		user.setLastName("waran");
		user.setEmailId("vigneshwarancareer@gmail.com");
		user.setMobile(8147855185l);
		user.setDob(new Date());
		user.setAddress(address);
		address.setUser(user);
		
		userRepo.saveOrUpdate(user);
		assertThat(user.getUserId()).isNotNull();
	}
	
	@Test
	public void testGetUserById() {
		User user = userRepo.getById(1l);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testGetUserByIdWithInvalidData() {
		User user = userRepo.getById(100l);
		assertThat(user).isNull();
	}
	
	@Test
	public void testDeleteUser() throws UserNotFoundException {
		/*
		 * Assert that the user is present in DB
		 * */
		assertThat(userRepo.getById(2l)).isNotNull();
		
		userRepo.delete(2l);
		
		User user =userRepo.getById(2l);
		assertThat(user).isNull();
	}
	
	@Test
	public void testDeleteUserWithUserObject() throws UserNotFoundException {
		/*
		 * Assert that the user is present in DB
		 * */
		User user = userRepo.getById(3l);
		assertThat(user).isNotNull();
		
		userRepo.delete(user);
		
		User testUser =userRepo.getById(3l);
		assertThat(testUser).isNull();
	}
	
}
