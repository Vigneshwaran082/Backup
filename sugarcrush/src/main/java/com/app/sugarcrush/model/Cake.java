package com.app.sugarcrush.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@DiscriminatorValue(value="ITEM-CAKE")
public class Cake extends Item {

	@Column
	private String flavour;
	
	@Column
	private String messageOnCake;

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public String getMessageOnCake() {
		return messageOnCake;
	}

	public void setMessageOnCake(String messageOnCake) {
		this.messageOnCake = messageOnCake;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,"flavour","messageOnCake");
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "flavour","messageOnCake");
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}


enum CakeType{
	EGG , EGGLESS
}