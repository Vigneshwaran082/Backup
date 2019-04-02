package com.app.sugarcrush.service;

import java.util.List;

import com.app.sugarcrush.model.Order;
import com.app.sugarcrush.model.User;

public interface OrderService {

	public Order getOrderbyOrderId(long orderId);
	
	public List<Order> getAllOrdersForUser(long userId);
	
	public List<Order> getAllOrdersForUser(User user);
	
	public void createOrderToUser(Order order ,User user);
	
	public void createOrderToUser(Order order ,long userId);
	
	public void deleteOrder(Order order);
	
	public void deleteOrder(long orderId);
	
	public Order updateOrder(Order order);
}
