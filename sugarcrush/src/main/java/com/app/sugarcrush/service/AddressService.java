package com.app.sugarcrush.service;

import com.app.sugarcrush.exception.UserNotFoundException;
import com.app.sugarcrush.model.Address;
import com.app.sugarcrush.model.User;

public interface AddressService {

	
	public Address getAddressByUserId(long userId);
	
	public void deleteAddressByUserId(long userId);
	
	public void deleteAddress(Address address);
	
	public Address updateAddressbyUserId(long userId,Address address);
	
	public Address updateAddress(Address address);
	
	public Address saveAddressForUser(User user , Address address) throws UserNotFoundException;
	
	public Address saveAddressForUser(long userId , Address address) throws UserNotFoundException;
	
	
	
}
