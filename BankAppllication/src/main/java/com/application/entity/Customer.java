package com.application.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String customerName;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@Min(18)
	@Max(100)
	private int age;
	
	@NotBlank
	@Email(message = "Invalid email address")
	private String email;
	
	@NotBlank(message = "please provide the phone number")
	private String phone;
	
	@NotBlank(message = "please provide the address")
	private String address;
	 
	private String isLogined;
	
	
	@Past(message = "please enter past date")
	private LocalDate dateOfBirth;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
    private Account account;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Beneficiary_ID")
	@JsonBackReference
    private	Customer beneficiary;
}
