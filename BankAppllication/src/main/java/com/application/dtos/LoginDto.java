package com.application.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginDto {
	
	@NotBlank(message = "please provide userId")
	private String userId;
	
	@NotBlank(message = "please provide the password")
	private String password;
}
