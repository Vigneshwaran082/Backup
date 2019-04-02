package com.mindtree.hotelmanagement.repo;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.hotelmanagement.model.Hotel;

public interface HotelRepo extends CrudRepository<Hotel, Long>  {

}
