package com.project.customer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	//@Column(nullable = false)
	//@NotBlank(message = "Location cannot be empty")
	private String location;
	
	@Column(nullable = false, unique = true)
	@Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits.")
	private String phone;
	
	@Column(nullable = false, unique = true)
	@Size(min = 5, message = "Username must be at least 5 characters long.")
	private String username;
	
	@Column(nullable = false)
	 @Pattern(
		        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
		        message = "Password must be at least 8 characters long and include at least 1 uppercase letter, 1 lowercase letter, and 1 number."
		    )
	private String password;

	public Customer() {}
	
	public Customer(Long id, String name, String location, String phone, String username, String password) {
	
		this.id = id;
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.username = username;
		this.password = password;
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
