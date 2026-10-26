package com.aits.vehicleservice.repository;

import com.aits.vehicleservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
