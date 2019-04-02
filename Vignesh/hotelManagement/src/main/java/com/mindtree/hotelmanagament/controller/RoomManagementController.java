package com.mindtree.hotelmanagament.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.hotelmanagement.exception.IllegalBookingException;
import com.mindtree.hotelmanagement.exception.NoRoomAvailableException;
import com.mindtree.hotelmanagement.model.Room;
import com.mindtree.hotelmanagement.service.HotelManagementService;
import com.mindtree.hotelmanagement.service.RoomManagementService;

@RestController
public class RoomManagementController {

	@Autowired
	RoomManagementService roomManagementService;
	
	@Autowired
	HotelManagementService hotelManagementService;
	
	@RequestMapping(value="/hotel/rooms/available",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> isRoomAvailable() {

		boolean isRoomAvailable = roomManagementService.isRoomAvailable();
		Map<String, String> status = new HashMap<String, String>();
		if (isRoomAvailable)
			status.put("status", "Rooms_Available");
		else
			status.put("status", "NO_ROOMS");
		return status;

	}

	@RequestMapping(value="/hotel/rooms",method=RequestMethod.GET)
	public List<Room> getAllAvailableRoomList() throws NoRoomAvailableException {

		List<Room> rooms = roomManagementService.getAllAvailableRoomList();

		if (rooms.isEmpty()) {
			throw new NoRoomAvailableException("No Rooms Available for booking");
		}

		return rooms;
	}

	@RequestMapping(value="/hotel/allrooms",method=RequestMethod.GET)
	public List<Room> getAllRoomList() throws NoRoomAvailableException {

		List<Room> rooms = roomManagementService.getAllRoomList();

		if (rooms.isEmpty()) {
			throw new NoRoomAvailableException("No Rooms present");
		}

		return rooms;
	}
	@RequestMapping(value="/hotel/room" ,method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Map addNewRoom(@RequestBody Room room) {
		Room persistedRoom = roomManagementService.createNewRoom(room);
		
		Map<String, String> responseStatus = new HashMap<String, String>();
		responseStatus.put("status", "Created_Successfully");
		return responseStatus;
	}

	@RequestMapping(value="/hotel/room/{id}",method=RequestMethod.DELETE)
	ResponseEntity<Map<String, String>> deleteRoom(@PathVariable("id") Long roomId) throws Exception {
		try {
			roomManagementService.deleteRoom(roomId);
		} catch (Exception e) {
			throw new NoRoomAvailableException("INVALID_ROOM_ID");
		}
		Map<String, String> responseStatus = new HashMap<String, String>();
		responseStatus.put("status", "Deleted_Successfully");
		ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<Map<String, String>>(responseStatus,
				HttpStatus.OK);

		return responseEntity;
	}

	@RequestMapping(value="/hotel/room/{id}/book",method=RequestMethod.GET)
	public ResponseEntity<Map<String, String>> updateRoomAsBooked(@PathVariable("id") Long roomId)
			throws NoRoomAvailableException  , IllegalBookingException{
		try {
			roomManagementService.updateRoomAsBooked(roomId);
		}catch(IllegalBookingException e) {
			throw new IllegalBookingException(e.getMessage());
		}catch (Exception e) {
			throw new NoRoomAvailableException("INVALID_ROOM_ID");
		}

		Map<String, String> responseStatus = new HashMap<String, String>();
		responseStatus.put("status", "Room_Booked_Successfully.Booked Room Id :"+ roomId);
		ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<Map<String, String>>(responseStatus,
				HttpStatus.OK);

		return responseEntity;
	}
	
	@RequestMapping(value="/hotel/room/{id}/vacate",method=RequestMethod.GET)
	public ResponseEntity<Map<String, String>> updateRoomAsAvailable(@PathVariable("id") Long roomId) throws IllegalBookingException{
		try {
			roomManagementService.updateRoomAsAvailable(roomId);
		}catch (Exception e) {
			throw new IllegalBookingException("INVALID_ROOM_ID");
		}
		
		Map<String, String> responseStatus = new HashMap<String, String>();
		responseStatus.put("status", "Room_Vacation_Successfully.Room Id :"+ roomId);
		ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<Map<String, String>>(responseStatus,
				HttpStatus.OK);

		return responseEntity;
    }
	

	@ExceptionHandler(NoRoomAvailableException.class)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> handleError(HttpServletRequest req, Exception ex) {
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", ((NoRoomAvailableException) ex).message);
		return status;
	}
	
	@ExceptionHandler(IllegalBookingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleIlegalBookingError(HttpServletRequest req, Exception ex) {
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", ((IllegalBookingException) ex).getMessage());
		return status;
	}
}
