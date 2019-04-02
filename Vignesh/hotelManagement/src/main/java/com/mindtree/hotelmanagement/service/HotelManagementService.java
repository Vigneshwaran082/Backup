package com.mindtree.hotelmanagement.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.hotelmanagement.model.Hotel;
import com.mindtree.hotelmanagement.model.Room;
import com.mindtree.hotelmanagement.repo.HotelRepo;

@Service
public class HotelManagementService {

	
	@Autowired
	HotelRepo hotelRepo;
	
	
	public Hotel getHotel() {
		Hotel hotel =  hotelRepo.findOne(1l);
		if(hotel == null) {
			hotel = createNewHotel();
		}
		return hotel;
	}
	
	
	public Hotel createNewHotel() {
		Hotel hotel = new Hotel();
		 hotel.setHotelName("Taj");
		return hotelRepo.save(hotel);
	}
	
	
	
	public Hotel addRoomToHotel(Room room , Hotel hotel) {
		  Set<Room> rooms = hotel.getRooms();
		  if(rooms == null || rooms.size() == 0) {
			  rooms  = new HashSet<Room>();
		  }
		  rooms.add(room);
		  hotel.setRooms(rooms);
		return hotelRepo.save(hotel);
	}
	
	/*public Hotel addRoom(Room room) {
		Hotel hotel = getHotel();
		Set<Room> rooms = hotel.getRooms();
		  if(rooms == null || rooms.size() == 0) {
			  rooms  = new HashSet<Room>();
		  }
		  rooms.add(room);
		  hotel.setRooms(rooms);
		return hotelRepo.save(hotel);
	}*/
	
}
