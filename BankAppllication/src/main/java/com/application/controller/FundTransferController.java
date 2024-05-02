package com.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dtos.FundTransferDto;
import com.application.response.Response;
import com.application.service.FundTransferService;

@RestController
@RequestMapping("/fund-transfer")
public class FundTransferController {
	
	private final FundTransferService fundTransferservice;
	
	 public FundTransferController(FundTransferService fundTransferservice ) {
		     this.fundTransferservice=fundTransferservice;
		}

	@PostMapping()
	public ResponseEntity<Response> fundTransfer(@RequestBody FundTransferDto transfer) {
		return fundTransferservice.fundTransfer(transfer);
	}
	
}
