package com.app.sugarcrush.repo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.sugarcrush.model.Image;
import com.app.sugarcrush.model.Item;
import com.app.sugarcrush.repo.ItemRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("junit")
@Sql(value = {"/item_test_script.sql" ,"/image_test_script.sql"})
@TestPropertySource(value="classpath:test_application.properties") 
public class ItemRepoTest {

	
	@Autowired
	ItemRepo itemRepo;
	
	static int count =0;
	
	@Test
	public void testGetAllImagesByItemId() {
		List<Image> images= itemRepo.getAllImagesByItemId(1l);
		assertThat(images).isNotEmpty();
		assertThat(images).size().isGreaterThanOrEqualTo(1);
		
	}
	
	@Test
	public void testGetAllImagesByItemIdWithInvalidData() {
		List<Image> images= itemRepo.getAllImagesByItemId(2l);
		assertThat(images).isEmpty();
	}
	
	
	@Test
	public void testGetAllAvailableItems() {
		List<Item> items = itemRepo.getAllAvailableItems();
		assertThat(items).isNotEmpty();
		assertThat(items).size().isGreaterThanOrEqualTo(1);
		assertThat(items.get(0).getImages()).isNotEmpty();
	}
	
	@Test
	public void testGetItemsByItemName() {
		List<Item> items = itemRepo.getItemsByItemName("Black-Forest");
		assertThat(items).isNotEmpty();
		assertThat(items).size().isGreaterThanOrEqualTo(1);
		assertThat(items.get(0).getImages()).isNotEmpty();
	}
	
	@Test
	public void testGetItemsByItemNameWithInvalidData() {
		List<Item> items = itemRepo.getItemsByItemName("TEST-CAKE-NAME");
		assertThat(items).isEmpty();
	}
}
