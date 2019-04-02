package com.mindtree.hotelbooking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.hotelbooking.exception.IllegalBookingException;
import com.mindtree.hotelbooking.model.Booking;
import com.mindtree.hotelbooking.service.BookingService;

@RestController
public class HotelbookingController {

	@Autowired
	BookingService bookingService;

	@RequestMapping(value = "/user/{userId}/bookings", method = RequestMethod.GET)
	public List<Booking> getAllBookings(@PathVariable("userId") Long userId) {
		return bookingService.getAllBookingByUserId(userId);
	}

	@RequestMapping(value = "/user/{userId}/accommodate", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public Booking newBooking(@PathVariable("userId") Long userId) throws IllegalBookingException {
		Booking booking = null;
		try {
			booking = bookingService.newBooking(userId);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new IllegalBookingException(e.getMessage());
		}
		return booking;
	}

	@RequestMapping(value = "/user/{userId}/booking/{bookingId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, String>> deleteBooking(@PathVariable("userId") Long userId,
			@PathVariable("bookingId") Long bookingId) throws IllegalBookingException {

		try {
			bookingService.deleteBooking(userId, bookingId);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new IllegalBookingException(e.getMessage());
		}
		
		Map<String, String> responseStatus = new HashMap<String, String>();
		responseStatus.put("status", "Deleted_Successfully");
		ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<Map<String, String>>(responseStatus,
				HttpStatus.OK);

		return responseEntity;

	}

	@ExceptionHandler(IllegalBookingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleIlegalBookingError(HttpServletRequest req, Exception ex) {
		ex.printStackTrace(System.out);
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", ((IllegalBookingException) ex).getMessage());
		return status;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleAllException(HttpServletRequest req, Exception ex) {
		ex.printStackTrace(System.out);
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", ex.getMessage());
		return status;
	}

}
