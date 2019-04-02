package com.mindtree.hotelbooking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.hotelbooking.model.Booking;

public interface BookingRepo  extends JpaRepository<Booking, Long>{

	@Query("select b from Booking b where b.bookingId =:bookingId and b.userId = :userId")
	Booking findBookingByUserIdAndBookingId(@Param("bookingId") Long bookingId ,@Param("userId") Long userId);
	
	@Modifying
	@Transactional
	@Query("delete from Booking b where b.roomId =:roomId and b.userId = :userId")
	int deleteBookingByUserIdAndRoomId(@Param("roomId") Long roomId ,@Param("userId") Long userId);

	@Query("select b from Booking b where b.userId = :userId")
	List<Booking> findBookingByUserId(@Param("userId") Long userId);

	
}
