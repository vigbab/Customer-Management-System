package com.project.customer.service;

import com.project.customer.model.Customer;
import com.project.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Register user
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	//View all users
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	//Find user by ID
	public Optional<Customer> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}
	
	//Update user details
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	//Delete user
	public void deleteCustomer(Long Id) {
		customerRepository.deleteById(Id);
	}
	
	// Login - Find by Username and Password
	public Optional<Customer> findByUsernameAndPassword(String username, String password) {
	    return customerRepository.findByUsernameAndPassword(username, password);
	}

	// Find Customers by Location
	public List<Customer> findByLocation(String location) {
	    return customerRepository.findByLocation(location);
	}

		
	
}
