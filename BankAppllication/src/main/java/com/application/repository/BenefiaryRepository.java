package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.entity.Beneficiary;

public interface BenefiaryRepository extends JpaRepository<Beneficiary, Integer> {

	public boolean existsByUserId(Integer userId);
	public boolean existsByBeneficiaryId(Integer beneficiaryId);
	public List<Beneficiary> findAllById(int id);
	
}
