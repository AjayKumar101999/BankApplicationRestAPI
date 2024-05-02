package com.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dtos.CustomerDto;
import com.application.dtos.LoginDto;
import com.application.entity.Customer;
import com.application.response.RegisterResponse;
import com.application.response.Response;
import com.application.service.CustomerService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/customer")
public class CustomerController { 
	
    private final CustomerService customerService;

	 public CustomerController(CustomerService customerService ) {
	     this.customerService=customerService;
	 }
	 
	String fls="false";
	String tr="true"; 

	@GetMapping("/json")
	public Customer getJson() {
		return new Customer();
	}
	
	@GetMapping("/detail/{customer-id}")
	public ResponseEntity<?> getuserdetail(@PathVariable(value = "customer-id") String id) {
		return customerService.getCustomerDetail(id);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<RegisterResponse> customerSignup(@RequestBody @Valid CustomerDto customer) {
	 return customerService.customerSignUp(customer);
		
	}

	@PostMapping("/sign-in")
	public ResponseEntity<Response> customerSignIn(@RequestBody @Valid LoginDto login) {
		return customerService.loginCustomer(login);
	}
	
	@PostMapping("/sign-out/{customer-id}")
	public ResponseEntity<Response> customerSignout(@PathVariable(value = "customer-id") String id) {
		return customerService.customerSignOut(id);
	}
}
