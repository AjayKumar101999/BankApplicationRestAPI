package com.application.response;

import java.util.List;

import com.application.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {

	private int statusCode;
	private List<Customer> customers;
}
