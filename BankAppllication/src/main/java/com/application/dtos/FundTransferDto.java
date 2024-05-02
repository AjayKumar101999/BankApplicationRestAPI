package com.application.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class FundTransferDto {

	@NotBlank(message = "please provide the sendor id")
	private String sendorId;
	
	@NotBlank(message = "please provide receiver id")
	private String recieverId;
	
	@NotBlank(message = "provide the amount")
	@Min(1)
	@Max(10000)
	private int amount;
	
}
