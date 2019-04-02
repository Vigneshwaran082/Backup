package com.app.sugarcrush.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.sugarcrush.exception.UserNotFoundException;
import com.app.sugarcrush.model.Address;
import com.app.sugarcrush.model.User;
import com.app.sugarcrush.repo.AddressRepo;
import com.app.sugarcrush.service.AddressService;
import com.app.sugarcrush.service.UserService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepo addressRepo;
	
	//@Autowired
	UserService userService;
	
	
	@Override
	public Address getAddressByUserId(long userId) {
		return userService.getUserbyId(userId).getAddress();
	}

	@Override
	public void deleteAddressByUserId(long userId) {
		User user  = userService.getUserbyId(userId);
		if(user.getAddress() != null) {
			deleteAddress(user.getAddress());
		}
	}

	@Override
	public void deleteAddress(Address address) {
			addressRepo.delete(address);
	}

	@Override
	public Address updateAddressbyUserId(long userId,Address address) {
		User user = userService.getUserbyId(userId);
		if(user.getAddress() !=null) {
		    /*
		     * If address already present , Just update else add new and update the user 
		     * */
		   Address addressToUpdate = user.getAddress();
		   addressToUpdate.setAddress1(address.getAddress1());
		   addressToUpdate.setAddress2(address.getAddress2());
		   addressToUpdate.setPinCode(address.getPinCode());
		   addressToUpdate.setUser(user);
		   updateAddress(addressToUpdate);
		   address = addressToUpdate;
		 }else {
			 user.setAddress(address);
			 userService.updateUser(user);
		}
		return address;
	}

	@Override
	public Address updateAddress(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public Address saveAddressForUser(User user, Address address) throws UserNotFoundException {
		if(user.getUserId() == null) {
			throw new UserNotFoundException();
		}
		user.setAddress(address);
		userService.updateUser(user);
		return address;
	}

	@Override
	public Address saveAddressForUser(long userId, Address address) throws UserNotFoundException {
		User user = userService.getUserbyId(userId);
		saveAddressForUser(user, address);
		return address;
	}

}
