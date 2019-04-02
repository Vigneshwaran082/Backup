package com.mindtree.hotelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.hotelmanagement.exception.IllegalBookingException;
import com.mindtree.hotelmanagement.model.Hotel;
import com.mindtree.hotelmanagement.model.Room;
import com.mindtree.hotelmanagement.repo.RoomRepo;

@Service
public class RoomManagementService {

	@Autowired
	RoomRepo roomRepo;

	@Autowired
	HotelManagementService hotelManagementService;

	public List<Room> getAllAvailableRoomList() {
		return roomRepo.getAllAvailableRooms();
	}
	
	public List<Room> getAllRoomList() {
		return roomRepo.findAll();
	}

	public boolean isRoomAvailable() {
		long allAvailableRoomsCount = roomRepo.getAllAvailableRoomsCount();
		return ((allAvailableRoomsCount > 0) ?true : false);
	}

	public Room createNewRoom(Room room) {
		Hotel hotel = hotelManagementService.getHotel();
		room.setHotel(hotel);
		room = roomRepo.save(room);
		hotelManagementService.addRoomToHotel(room, hotel);
		//hotelManagementService.addRoom(room);
		return room;
	}

	public void deleteRoom(Long roomId) {
		roomRepo.delete(roomId);
	}

	public Room updateRoom(Room room) {
		return createNewRoom(room);
	}

	public boolean isRoomAvailable(Long roomId) {
		return roomRepo.isRoomAvailable(roomId);
	}
	
	
	public void updateRoomAsBooked(Long roomId) throws IllegalBookingException {
		if(isRoomAvailable(roomId)) {
			roomRepo.updateRoomAsBooked(roomId); 
		}else {
			throw new IllegalBookingException("ROOM-BOOKED-ALREADY");
		}
	}

	public void updateRoomAsAvailable(Long roomId) throws IllegalBookingException {
			roomRepo.updateRoomAsAvailable(roomId); 
		
	}
}
