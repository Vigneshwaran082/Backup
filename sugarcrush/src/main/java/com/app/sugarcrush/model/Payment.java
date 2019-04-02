package com.app.sugarcrush.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_id")
	private Long paymentId;
	
	@OneToOne
	private Order order;
	
	@Column(name="payment_type")
	@Enumerated(value=EnumType.STRING)
	private PaymentType paymentType = PaymentType.COME_AND_GRAB_ORDER;
	
	@Column(name="is_payment_completed")
	private Boolean isPaymentCompleted = false;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Boolean getIsPaymentCompleted() {
		return isPaymentCompleted;
	}

	public void setIsPaymentCompleted(Boolean isPaymentCompleted) {
		this.isPaymentCompleted = isPaymentCompleted;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,  "paymentId","order","paymentType");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "paymentId","order","paymentType");
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	

}


enum PaymentType{
	CASH_ON_DELIVERY,CREDIT_CARD,DEBIT_CARD,NET_BANKING,COME_AND_GRAB_ORDER
}