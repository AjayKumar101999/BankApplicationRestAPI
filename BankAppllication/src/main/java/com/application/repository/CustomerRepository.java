package com.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByEmail(String email);
	public boolean existsByEmail(String email);
	public boolean existsById(Integer id);
	public Optional<Customer> findById(Integer id);
	public boolean existsByUsername(String username);
	public boolean existsByPassword(String password);
	public Customer findByUsername(String username);

	
}
