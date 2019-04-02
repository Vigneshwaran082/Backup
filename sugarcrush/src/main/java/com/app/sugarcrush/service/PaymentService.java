package com.app.sugarcrush.service;

import com.app.sugarcrush.model.Order;
import com.app.sugarcrush.model.Payment;

public interface PaymentService {

	public Payment createPaymentForOrder(Payment payment , Order order);
	
	public Payment createPaymentForOrder(Payment payment , long orderId);
	
	public Payment updatePaymentForOrderAsCompleted(long orderId);
	
	public Payment updatePaymentForOrderAsCompleted(Order orderId);
	
	public Payment getPaymentForOrder(long orderId);
	
	public Payment getPayment(long paymentId);
	
	public void deletePayment(long paymentId);
	
	public void deletePaymentForOrder(long orderId);
	
	
}
