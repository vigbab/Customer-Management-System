package com.project.customer.controller;

import com.project.customer.model.Customer;
import com.project.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
		//Register Customer
	@PostMapping("/register")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
	    if (customer.getName() == null || customer.getUsername() == null || customer.getPassword() == null || customer.getLocation() == null || customer.getPassword() == null) {
	        return ResponseEntity.badRequest().body("All fields are required.");
	    }

	    Customer savedCustomer = customerService.saveCustomer(customer);
	    return ResponseEntity.status(201).body(savedCustomer);
	}

		//View all customers
	@GetMapping
	public ResponseEntity<?> getAllCustomers(){
	    List<Customer> customers = customerService.getAllCustomers();
	    if (customers.isEmpty()) {
	        return ResponseEntity.status(204).body("No customers found.");
	    }
	    customers.forEach(c -> c.setPassword(null)); // Hide password

	    return ResponseEntity.ok(customers);
	}

		//Get Customer by ID
	@GetMapping("/{id}")
		public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
			Optional<Customer> customer = customerService.getCustomerById(id);
			if (customer.isPresent()) {
				Customer c = customer.get();
				c.setPassword(null); // Hide password
			        return ResponseEntity.ok(c);
			} else {
				return ResponseEntity.status(404).body("Customer with ID " + id + " not found.");
			}
		}

		// Update Customer
	@PutMapping("/update")
		public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
			Optional<Customer> existing = customerService.getCustomerById(customer.getId());
			if (existing.isPresent()) {
				Customer updated = customerService.updateCustomer(customer);
				return ResponseEntity.ok(updated);
			} else {
				return ResponseEntity.status(404).body("Cannot update. Customer not found.");
			}
		}

		// Delete Customer
	@DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
			Optional<Customer> customer = customerService.getCustomerById(id);
			if (customer.isPresent()) {
				customerService.deleteCustomer(id);
				return ResponseEntity.ok("Customer deleted successfully.");
			} else {
				return ResponseEntity.status(404).body("Customer with ID " + id + " not found.");
			}
		}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Customer loginRequest) {
	    Optional<Customer> customer = customerService.findByUsernameAndPassword(
	        loginRequest.getUsername(), loginRequest.getPassword()
	    );

	    if (customer.isPresent()) {
	        return ResponseEntity.ok(customer.get()); // OK to return password here
	    } else {
	        return ResponseEntity.status(401).body("Invalid username or password.");
	    }
	}
	@GetMapping("/location/{location}")
	public ResponseEntity<?> getCustomersByLocation(@PathVariable String location) {
	    List<Customer> customers = customerService.findByLocation(location);

	    if (customers.isEmpty()) {
	        return ResponseEntity.status(404).body("No customers found in location: " + location);
	    }

	    // Extract names only
	    List<String> names = customers.stream().map(Customer::getName).toList();

	    return ResponseEntity.ok(names);
	}

	/*
	 * 
	 * {
  "name": "John Doe",
  "location": "New York",
  "phoneNumber": "1234567890",
  "username": "johndoe",
  "password": "Password@123"
}
REGISTER
curl -X POST http://localhost:8080/customers/register -H "Content-Type: application/json" -d '{"name": "Jane Doe", "username": "janedoe123", "password": "Secret123", "location": "New York", "phone": "1234567890"}'
LOGIN
curl -X POST http://localhost:8080/customers/login -H "Content-Type: application/json" -d '{"username": "janedoe123", "password": "Secret123"}'
GET ALL
curl -X GET http://localhost:8080/customers
GET BY ID
curl -X GET http://localhost:8080/customers/1
UPDATE
curl -X PUT http://localhost:8080/customers/update -H "Content-Type: application/json" -d '{"id": 1, "name": "Jane Doe", "username": "janedoe123", "password": "Secret123", "location": "Boston", "phone": "1234567890"}'
GET BY LOCATION
curl -X GET http://localhost:8080/customers/location/New%20York
DELETE
curl -X DELETE http://localhost:8080/customers/delete/1
	 *
	 */
	
	
}
