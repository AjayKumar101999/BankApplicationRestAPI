package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account findByAccnumber(long accnumber);

}
