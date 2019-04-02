package com.app.sugarcrush.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sugarcrush.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
