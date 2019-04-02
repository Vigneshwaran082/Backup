package com.mindtree.hotelbooking.util;

public class UrlConstantsUtil {

	//private static String BOOKING_DOMAIN_NAME = "http://localhost:9090/";
	//private static String USER_DOMAIN_NAME = "http://localhost:7070/";
	
	
	public static String BOOKING_IS_ROOM_AVAILABLE ="hotel/rooms/available";
	public static String BOOKING_GET_ALL_AVAILABLE_ROOMS = "hotel/rooms";
	
	public static String USER_GET_ALL_USER = "getAllUser";
	public static String USER_CREATE_USER = "registerUser";
	
	
	public static String buildBookRoomURL(Long roomId) {
		return "hotel/room/" +roomId.toString()+"/book";
	}
	
	public static String buildDeleteRoomUrl(Long roomId) {
		return  "/hotel/room/"+roomId;
	}
	
	public static String buildVacateRoomUrl(Long roomId) {
		return  "/hotel/room/"+roomId.toString()+"/vacate";
	}
	
	
	public static String buildDeleteUserUrl(Long userId) {
		return  "/deleteUser/"+userId;
	}
	
}


