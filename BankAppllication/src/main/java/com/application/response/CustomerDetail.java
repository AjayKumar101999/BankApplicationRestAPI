package com.application.response;

import com.application.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDetail{
	
	private int statusCode;
	private	String message;
	private Customer customer;

}
