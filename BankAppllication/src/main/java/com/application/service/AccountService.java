package com.application.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.application.dtos.UserDto;
import com.application.entity.Account;
import com.application.repository.AccountRepository;

@Service
public class AccountService {
	
	public final AccountRepository accountrepo; 
	
	public AccountService(AccountRepository accountrepo) {
		this.accountrepo=accountrepo;
	}

	public long  generateAccountNumber() { 
		Random random = new Random();
		return  Math.round(random.nextFloat() * Math.pow(10,12));
	  } 

	public Account save(Account account) {
		return accountrepo.save(account);
		
	}
	
	public Account findByAccnumber(long accnumber) {
		return accountrepo.findByAccnumber(accnumber);
	}

	
}

