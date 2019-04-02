package com.app.sugarcrush.service;

import java.util.List;

import com.app.sugarcrush.model.Image;
import com.app.sugarcrush.model.Item;

public interface ItemService {

	public List<Item> getAllItems(int offSet, int max);
	
	public Item getItemById(Long itemId);
	
	public List<Image> getAllImagesByItemId(Long itemId);
	
	public List<Item> getAllAvailableItems(int offSet,int max);
	
	public List<Item> getItemsByItemName(String itemName);
}
