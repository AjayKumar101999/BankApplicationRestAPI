package com.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dtos.BeneficiaryDto;
import com.application.response.CustomerDetail;
import com.application.response.Response;
import com.application.service.BeneficiaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {
	
	private final BeneficiaryService beneficiaryService;
	
	public BeneficiaryController(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	@GetMapping("/detail/{customer-id}")
	public ResponseEntity<CustomerDetail> getuser(@PathVariable(value = "customer-id") String id) {
			return beneficiaryService.getcustomerdetail(id);
	}

	@PostMapping("/add")
	public ResponseEntity<Response> registerUser(@RequestBody @Valid BeneficiaryDto beneficiary) {
		return beneficiaryService.addBeneficiary(beneficiary);
	}
	
}
