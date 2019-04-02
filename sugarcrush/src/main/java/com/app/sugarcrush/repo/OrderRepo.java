package com.app.sugarcrush.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sugarcrush.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

}
