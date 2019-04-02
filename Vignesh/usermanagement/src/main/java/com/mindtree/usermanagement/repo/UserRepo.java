package com.mindtree.usermanagement.repo;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.usermanagement.model.User;

public interface UserRepo extends CrudRepository<User, Long>{

	// CRUD REPO DOes All Actions behind the scene
	
}
