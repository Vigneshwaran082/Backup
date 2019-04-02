package com.mindtree.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table
public class User {

	
	@Id
	@GeneratedValue
	private Long userId;
	
	
	@Column
	@NotNull
	@Size(min=3 ,  max =100 , message = "First name should have more than 3 character")
	private String firstName = "Guest";
	
	@Column
	private String lastName;
	
	
	@Column
	private String emailId;
	
	@Column
	private Long mobile;
	
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		String tempLastName = (lastName != null)?lastName:"";
		return firstName + tempLastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	
	
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,"userId","firstName","lastName" ,"emailId","mobile");
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj,"userId" ,"firstName","lastName" ,"emailId","mobile","dob");
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
