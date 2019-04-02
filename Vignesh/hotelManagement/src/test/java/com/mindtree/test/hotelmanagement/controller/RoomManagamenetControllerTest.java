package com.mindtree.test.hotelmanagement.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.hotelmanagement.model.Hotel;
import com.mindtree.hotelmanagement.model.Room;
import com.mindtree.hotelmanagement.service.HotelManagementService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomManagamenetControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	HotelManagementService hotelManagementService;
	
	static int count =0;
	
	@Before
	public  void createHotelAndRooms() {
		if(count == 0) {
		 Hotel hotel = hotelManagementService.createNewHotel();
		 Room room1 = new Room();
		 room1.setHotel(hotel);
		 Room room2 = new Room();
		 room2.setHotel(hotel);
		 Room room3 = new Room();
		 room3.setHotel(hotel);
		 Room room4 = new Room();
		 room4.setHotel(hotel);
		 Room room5 = new Room();
		 room5.setHotel(hotel);
		 
		 hotelManagementService.addRoomToHotel(room1, hotel);
		 hotelManagementService.addRoomToHotel(room2, hotel);
		 hotelManagementService.addRoomToHotel(room3, hotel);
		 hotelManagementService.addRoomToHotel(room4, hotel);
		 hotelManagementService.addRoomToHotel(room5, hotel);
		 count++;
		}
		
		
	}
	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetAllRoomsAvailable() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("/hotel/rooms/available", List.class);


		List<Room> rooms = responseEntity.getBody();
		assertEquals(5, rooms.size());

	}
	
	
	
	
}
