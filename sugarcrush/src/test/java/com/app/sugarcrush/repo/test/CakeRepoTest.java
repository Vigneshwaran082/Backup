package com.app.sugarcrush.repo.test;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.sugarcrush.model.Cake;
import com.app.sugarcrush.repo.CakeRepo;


/*
 * This is feature in spring boot called Context-slicing by using
 *  this feature we can test only Repository or controller or JSON data 
 * */
@DataJpaTest
/*
 * By default tries to load the in-memory Database for
 *  testing repository[If we use @DataJpaTest] but if we want 
 *  to use the Pre-existing Database then use the below annotation
 * */
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@TestPropertySource(value="classpath:test_application.properties") 
@ActiveProfiles("junit")
@Sql("/cake_test_script.sql")
public class CakeRepoTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	CakeRepo cakeRepo;
	
	
	@Test
	public void testGetCakesByFlavour() {
		List<Cake> cakes = cakeRepo.getCakesByFlavour("CHOCOLATE");
		Assertions.assertThat(cakes).isNotEmpty();
		Assertions.assertThat(cakes).size().isGreaterThanOrEqualTo(1);
	}
	
	@Test
	public void testGetCakesByFlavourWithInvalidData() {
		List<Cake> cakes = cakeRepo.getCakesByFlavour("VANILLA");
		Assertions.assertThat(cakes).isEmpty();
		Assertions.assertThat(cakes).size().isEqualTo(0);
	}
	
}




/*
 * NOTE :
 *  @Transactional 
 *  @Rollback(true)
 *   Can be used in Test cases to make the test persist to DB or roll-back to original state
 * */
