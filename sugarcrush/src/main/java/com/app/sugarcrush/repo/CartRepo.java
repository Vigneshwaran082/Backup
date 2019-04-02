package com.app.sugarcrush.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sugarcrush.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

}
