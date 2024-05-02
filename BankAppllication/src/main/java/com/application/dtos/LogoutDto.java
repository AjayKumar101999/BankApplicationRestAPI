package com.application.dtos;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LogoutDto {
	
	@Email(message = "invalid email address")
	private String email;

}
