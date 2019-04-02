package com.mindtree.hotelmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.hotelmanagement.model.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {
	
	
	@Query("select r from Room r where r.isAvailable = true")
	public List<Room> getAllAvailableRooms();

	@Query("select isAvailable from Room where roomId = :roomId")
	public Boolean isRoomAvailable(@Param("roomId") Long roomId);
	
	@Modifying
	@Transactional
	@Query("update Room r Set  r.isAvailable = false where r.roomId = :roomId")
	public void updateRoomAsBooked(@Param("roomId") Long roomId);
	
	@Modifying
	@Transactional
	@Query("update Room r Set  r.isAvailable = true where r.roomId = :roomId")
	public void updateRoomAsAvailable(@Param("roomId") Long roomId);
	
	@Query("select count(*) from Room r where r.isAvailable = true")
	public Long getAllAvailableRoomsCount();
	
	
	
	
}

