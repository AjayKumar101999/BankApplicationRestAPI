package com.application.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CustomerDto {
	
	@NotBlank
	@Size(min = 5, message = "username should have minimum 5 characters")
	private String customerName;
	
	@Min(18)
	@Max(100)
	private int age;
	
	@NotBlank
	@Email(message = "Invalid email address")
	private String email;
	
	@NotBlank(message = "please provide phone")
	@Size(min = 10, max = 10, message = "invalid phone number")
	private String phone;
	
	@NotBlank
	private String address;
	
	@NotNull(message = "please provide the date of birth")
	@Past(message = "please enter past date")
	private LocalDate dateOfBirth;
	
	@Min(1000)
	private int amount;
	
	@NotBlank(message = "please provide the account type! like saving or current!")
	private String accountType;
	
	@NotBlank(message = "please provide the banch name")
	private String branch;
}
