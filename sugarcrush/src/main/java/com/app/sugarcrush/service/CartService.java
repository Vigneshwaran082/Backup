package com.app.sugarcrush.service;

import java.util.List;

import com.app.sugarcrush.model.Cart;
import com.app.sugarcrush.model.Item;
import com.app.sugarcrush.model.Order;

public interface CartService {

	
	public Cart getCartForOrder(long orderId);
	
	public Cart getCartByCartId(long cardId);
	
	public void deleteCartItems(long cartId);
	
	public Cart addCartToOrder(Cart cart, Order order);
	
	public Cart addCartToOrder(Cart cart, long orderId);
	
	public Cart updateCart(Item item);
	
	public Cart addItemsToCart(List<Item> item);
	
}
