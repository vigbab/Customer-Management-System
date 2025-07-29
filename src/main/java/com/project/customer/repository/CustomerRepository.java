package com.project.customer.repository;

import com.project.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByUsernameAndPassword(String username, String password);

    List<Customer> findByLocation(String location);
	
}
