package com.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BeneficiaryDto {
	
	@NotBlank(message = "please provide the customerId")
	private String customerId;
	@NotBlank(message = "please provide the beneficairyId")
	private String beneficiaryId;

}



