package com.mindtree.hotelbooking.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.hotelbooking.exception.IllegalBookingException;
import com.mindtree.hotelbooking.model.Booking;
import com.mindtree.hotelbooking.model.Room;
import com.mindtree.hotelbooking.model.User;
import com.mindtree.hotelbooking.repo.BookingRepo;
import com.mindtree.hotelbooking.util.UrlConstantsUtil;

@Service
public class BookingService {

	@Autowired
	BookingRepo bookingRepo;

	@Autowired
	private DiscoveryClient discoveryClient;

	private static String bookingServiceBaseUrl;
	private static String userServiceBaseUrl;

	@SuppressWarnings("rawtypes")
	public Booking newBooking(Long userId) throws Exception {
		
		Booking booking = null;

		boolean isUserAvailable = isUserAvailable(userId);
		if(!isUserAvailable) {
			throw new IllegalBookingException("User NOT FOUND");
		}
		Long roomId = getAvailableRoomId();
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<HashMap> response = restTemplate.exchange(
				getBaseUrlForHotelManagementService() + UrlConstantsUtil.buildBookRoomURL(roomId), HttpMethod.GET, null,
				HashMap.class);

		HttpStatus httpStatus = response.getStatusCode();
		if ((HttpStatus.OK.toString().equals(httpStatus.toString())) && isUserAvailable) {
			booking = createBooking(userId, roomId);
		} else {
			throw new IllegalBookingException("ROOM_NOT_AVAILABLE");
		}

		return booking;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Long getAvailableRoomId() throws IllegalBookingException, Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				getBaseUrlForHotelManagementService() + UrlConstantsUtil.BOOKING_GET_ALL_AVAILABLE_ROOMS,
				HttpMethod.GET, null, String.class);
		HttpStatus httpStatus = response.getStatusCode();

		String jsonContent = response.getBody();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(jsonContent);
		if ((HttpStatus.OK.toString().equals(httpStatus.toString()))
				&& !jsonContent.contains("No Rooms Available for booking")) {
			List<Room> rooms = mapper.readValue(jsonContent,
					mapper.getTypeFactory().constructCollectionType(List.class, Room.class));
			Room room = rooms.get(0);
			return room.getRoomId();
		} else {
			throw new IllegalBookingException("NO_ROOMS_AVAILABLE");
		}
	}

	public boolean deleteBooking(Long userId, Long bookingId)
			throws IllegalBookingException, JsonParseException, JsonMappingException, IOException {
		Booking booking = bookingRepo.findBookingByUserIdAndBookingId(bookingId, userId);
		boolean isBookingDeleted = false;
		if (booking == null) {
			throw new IllegalBookingException(
					"NO_BOOKING_AVAILABLE_FOR_Booking-ID:" + bookingId + " USER-ID:" + userId);
		} else {
			boolean isUserAvailable = isUserAvailable(userId);
			boolean isRoomVacated = vacateRoom(booking.getRoomId());
			if (isRoomVacated && isUserAvailable) {
				bookingRepo.deleteBookingByUserIdAndRoomId(booking.getRoomId(), userId);
				isBookingDeleted = true;
			}

		}
		return isBookingDeleted;

	}

	@SuppressWarnings("rawtypes")
	private boolean vacateRoom(Long roomId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				getBaseUrlForHotelManagementService() + UrlConstantsUtil.buildVacateRoomUrl(roomId), HttpMethod.GET,
				null, String.class);
		HttpStatus httpStatus = response.getStatusCode();
		boolean isRoomVacated = false;
		if (HttpStatus.OK.toString().equals(httpStatus.toString())) {
			isRoomVacated = true;
		}
		return isRoomVacated;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean isUserAvailable(Long userId) throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				getBaseUrlForUserManagementService() + UrlConstantsUtil.USER_GET_ALL_USER, HttpMethod.GET, null,
				String.class);

		boolean isUserAvailable = false;
		String jsonContent = response.getBody();
		ObjectMapper mapper = new ObjectMapper();
		List<User> users = mapper.readValue(jsonContent,
				mapper.getTypeFactory().constructCollectionType(List.class, User.class));
		for (User user : users) {
			if (user.getUserId().toString().equals(userId.toString())) {
				isUserAvailable = true;
				break;
			}
		}
		return isUserAvailable;
	}

	public List<Booking> getAllBookingByUserId(Long userId) {
		return bookingRepo.findBookingByUserId(userId);

	}

	public Booking createBooking(Long userId, Long roomId) {
		Booking booking = new Booking();
		booking.setRoomId(roomId);
		booking.setUserId(userId);
		bookingRepo.save(booking);
		bookingRepo.flush();
		return booking;
	}

	private String getBaseUrlForHotelManagementService() {
		if (bookingServiceBaseUrl == null) {
			List<ServiceInstance> instances = discoveryClient.getInstances("HOTEL_ROOM_MANAGEMENT_SERVICE");
			ServiceInstance serviceInstance = instances.get(0);
			bookingServiceBaseUrl = serviceInstance.getUri().toString();
		}
		return bookingServiceBaseUrl;
	}

	private String getBaseUrlForUserManagementService() {
		if (userServiceBaseUrl == null) {
			List<ServiceInstance> instances = discoveryClient.getInstances("USER_MANAGEMENT");
			ServiceInstance serviceInstance = instances.get(0);
			userServiceBaseUrl = serviceInstance.getUri().toString();
		}
		return userServiceBaseUrl;
	}

}
