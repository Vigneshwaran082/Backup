package com.app.sugarcrush.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.sugarcrush.model.Image;
import com.app.sugarcrush.model.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long>{

	@Query("select item.images from Item item  where item.itemId = :itemId")
	public List<Image> getAllImagesByItemId(@Param("itemId") Long itemId);
	
	@Query("select item from Item item  where item.isAvailable = true")
	public List<Item> getAllAvailableItems();
	
	@Query("select item from Item item  where item.itemName like %:itemName%")
	public List<Item> getItemsByItemName(@Param("itemName") String itemName);
	
}
