package com.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.dtos.BeneficiaryDto;
import com.application.entity.Customer;
import com.application.exception.BeneficiaryAddedAlreadyException;
import com.application.exception.CustomerDoesNotExistException;
import com.application.response.CustomerDetail;
import com.application.response.Response;

@Service
public class BeneficiaryService {
	
private final CustomerService customerService;
	
	@Autowired
	public BeneficiaryService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public ResponseEntity<CustomerDetail> getcustomerdetail(String id) {
		if(!(customerService.existsByUsername(id)))
			throw new CustomerDoesNotExistException("Customer does not exist");
			
		Customer beneficiaryDetail = customerService.findByUsername(id).getBeneficiary(); 
		
		if(beneficiaryDetail==null)
			throw new CustomerDoesNotExistException("Beneficiary not added yet!");
		return new ResponseEntity<>(new CustomerDetail(200, "below is the beneficiary detail", beneficiaryDetail), HttpStatus.OK);

	}

	public ResponseEntity<Response> addBeneficiary(BeneficiaryDto beneficiary) {
		if(!(customerService.existsByUsername(beneficiary.getCustomerId())))
			throw new CustomerDoesNotExistException("Customer" +beneficiary.getCustomerId() +" does not exist");
		
		if(!(customerService.existsByUsername(beneficiary.getBeneficiaryId())))
			throw new CustomerDoesNotExistException("Customer" +beneficiary.getBeneficiaryId() +" does not exist");
		
		Customer customer = customerService.findByUsername(beneficiary.getCustomerId());
		if(!(customer.getBeneficiary()==null))
			throw new BeneficiaryAddedAlreadyException("Beneficiary added already");
		
		Customer addbeneficiary=customerService.findByUsername(beneficiary.getBeneficiaryId());
		customer.setBeneficiary(addbeneficiary);
		customerService.save(customer);

		return new ResponseEntity<>(new Response(200, "beneficiary added successfully"), HttpStatus.OK);
		}
//		if(customer.getBeneficiary().getUsername().equals(beneficiary.getBeneficiaryId()))
	}
	
	
