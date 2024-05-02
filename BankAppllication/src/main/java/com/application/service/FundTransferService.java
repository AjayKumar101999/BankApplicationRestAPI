package com.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.dtos.FundTransferDto;
import com.application.entity.Customer;
import com.application.exception.AccountDoesnotExistException;
import com.application.exception.AmountMustGreaterThanZeroException;
import com.application.exception.InsufficiantAmountException;
import com.application.response.Response;

@Service
public class FundTransferService {
	
	private final CustomerService customerService;
	
	public FundTransferService(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	public ResponseEntity<Response> fundTransfer(FundTransferDto transfer) {
			if (!(customerService.existsByUsername(transfer.getSendorId()))) 
				throw new AccountDoesnotExistException("Customer "+ transfer.getSendorId()+" not exist");
			
			if (!customerService.existsByUsername(transfer.getRecieverId())) 
				throw new AccountDoesnotExistException("Customer "+ transfer.getSendorId()+" not exist");
			
			Customer sendor = customerService.findByUsername(transfer.getSendorId());
			Customer receiver = customerService.findByUsername(transfer.getRecieverId());
			
			if(transfer.getAmount()<=0)
				throw new AmountMustGreaterThanZeroException("Transfer amount should be greater than 0 ");
			
			if(sendor.getAccount().getBalance()<transfer.getAmount()) 
				throw new InsufficiantAmountException("insufficient amount in user "+transfer.getSendorId()+" account ");
			
			if(sendor.getBeneficiary()==null) {
				return new ResponseEntity<>(new Response(400, "beneficiary not added yet"), HttpStatus.BAD_REQUEST); 
			}					
			sendor.getAccount().setBalance(sendor.getAccount().getBalance() - transfer.getAmount());
			receiver.getAccount().setBalance(receiver.getAccount().getBalance() + transfer.getAmount());
			
			customerService.save(sendor);
			customerService.save(receiver);
			return new ResponseEntity<>(new Response(200, "Amount transferred successfully "), HttpStatus.OK); 
	}
}
