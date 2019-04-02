package com.mindtree.hotelbooking.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.hotelbooking.model.Booking;
import com.mindtree.hotelbooking.model.User;
import com.mindtree.hotelbooking.util.UrlConstantsUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelbookingControllerTest {

	

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetAllBookings() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("/user/1/bookings", List.class);
		List<Booking> bookings = responseEntity.getBody();
		assertEquals(HttpStatus.OK,  responseEntity.getStatusCode());

	}

	
	@Test
	public void testNewBooking() {
		ResponseEntity<Booking> responseEntity = restTemplate.getForEntity("/user/1/accommodate", Booking.class);
		Booking bookings = responseEntity.getBody();
		assertNotNull(bookings);
		assertEquals(HttpStatus.CREATED,  responseEntity.getStatusCode());

	}

	 
	@Test
	public void testDeleteBooking() {
		//CREATE NEW USER
		Long userId =createUser();
		
		//CREATE NEW BOOKING
		Long bookingId = bookARoom(userId);
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/user/"+ userId +"/	/"+bookingId, String.class);
		String status = responseEntity.getBody();
		assertTrue(status.contains("Deleted_Successfully"));
		assertEquals(HttpStatus.OK,  responseEntity.getStatusCode());

		//Finally delete the user 
		deleteUserByUserId(userId);
	}

	
	private Long bookARoom(Long userId) {
		ResponseEntity<Booking> responseEntity = restTemplate.getForEntity("/user/"+userId.toString()+"/accommodate", Booking.class);
		Booking bookings = responseEntity.getBody();
		assertNotNull(bookings);
		assertEquals(HttpStatus.CREATED,  responseEntity.getStatusCode());
		Long bookingId =  bookings.getBookingId();
		
		//TODO: FIX THIS
		return (bookingId == null) ? bookings.getRoomId() : bookingId;
	}
	
	private Long createUser() {
		User user = new User();
		user.setFirstName("Rajesh_" + new Random(100).nextLong());
		user.setLastName("Kumar");
		user.setMobile(8147855185l);
		user.setEmailId("rajesh@mindtree.com");
		
		
		ResponseEntity<User> responseEntity = restTemplate.postForEntity(getBaseUrlForUserManagementService()+UrlConstantsUtil.USER_CREATE_USER, user, User.class);
		return responseEntity.getBody().getUserId();
	}
	
	
	private void deleteUserByUserId(Long userId) {
		restTemplate.delete(UrlConstantsUtil.buildDeleteUserUrl(userId));
	}
	
	
	private String getBaseUrlForUserManagementService() {
		
			List<ServiceInstance> instances = discoveryClient.getInstances("USER_MANAGEMENT");
			ServiceInstance serviceInstance = instances.get(0);
		
		return serviceInstance.getUri().toString();
	
	}
	
	
}





/*@MockBean
private BookingRepo bookingRepo;

@Before
public void setUp() {
    Booking booking = new Booking();
    booking.setBookingId(1l);
    booking.setUserId(1l);
    booking.setRoomId(1l);
 
    Booking secondBooking = new Booking();
    secondBooking.setBookingId(2l);
    secondBooking.setUserId(1l);
    secondBooking.setRoomId(2l);
 
    List<Booking> bookings = new ArrayList<Booking>();
    bookings.add(booking);
    bookings.add(secondBooking);
    
    
    Mockito.when(bookingRepo.findBookingByUserId(1l))
      .thenReturn(bookings);
}


@SuppressWarnings({ "unchecked", "rawtypes" })
@Test
public void testGetAllBookings() {
	ResponseEntity<List> responseEntity = restTemplate.getForEntity("/user/1/bookings", List.class);
	List<Booking> bookings = responseEntity.getBody();
	assertEquals(2, bookings.size());
}
*/
