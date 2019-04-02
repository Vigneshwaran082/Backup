package com.mindtree.test.hotelmanagement.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindtree.hotelmanagement.model.Room;
import com.mindtree.hotelmanagement.service.RoomManagementService;
import com.mindtree.test.hotelmanagement.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ TestConfig.class })

public class RoomManagementServiceTest {

	
	@Autowired
	RoomManagementService roomManagementService;
	
	
	
	@Test
	public void testCreateNewRoom() {
		Room room = new Room();
		room.setIsAvailable(true);
		assertNotNull(roomManagementService.createNewRoom(room));
		
				
	}
	
	
}
