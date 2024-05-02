package com.application.response;


import java.util.List;

import com.application.entity.Beneficiary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BeneficiaryList {
	private int statusCode;
	private String message;
	private List<Beneficiary> beneficiary;

}
