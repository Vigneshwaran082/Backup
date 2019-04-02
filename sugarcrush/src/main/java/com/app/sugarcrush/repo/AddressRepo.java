package com.app.sugarcrush.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sugarcrush.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}
