package com.mindtree.hotelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.mindtree" })
@EnableEurekaClient
public class HotelManagementApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
		
	}
	
	
	
}
