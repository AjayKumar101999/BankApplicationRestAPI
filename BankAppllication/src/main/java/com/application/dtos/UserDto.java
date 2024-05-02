package com.application.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;


@Data
public class UserDto {
	
	@NotBlank
	private String username;
	
	@Min(18)
	@Max(100)
	private int age;
	
	@NotBlank
	@Email(message = "Invalid email address")
	private String email;
	private String phone;
	private String address;
	
	@Past(message = "please enter past date")
	private LocalDate dateOfBirth;

	private int amount;
	private String accountType;
	private String branch;
}
