package com.application.service;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.dtos.CustomerDto;
import com.application.dtos.LoginDto;
import com.application.entity.Account;
import com.application.entity.Customer;
import com.application.exception.AmountMustGreaterThanZeroException;
import com.application.exception.CustomerDoesNotExistException;
import com.application.exception.SavingOrCurrentAccountException;
import com.application.exception.UserLoggedInAlreadyException;
import com.application.exception.UserNotloggedInException;
import com.application.exception.UserRegisteredAlreadyException;
import com.application.exception.UsernamePasswordnotExistException;
import com.application.repository.CustomerRepository;
import com.application.response.Credential;
import com.application.response.CustomerDetail;
import com.application.response.RegisterResponse;
import com.application.response.Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	String tr = "true";
	String fls = "false";

	private final CustomerRepository customerrepo;
	private final AccountService accountService;
	
	public CustomerService(CustomerRepository customerrepo, AccountService accountService) {
		this.customerrepo = customerrepo;
		this.accountService = accountService;
	}

	public  boolean existsByEmail(String email) {
		return customerrepo.existsByEmail(email);
	}
	
	
	public  boolean existsByUsername(String username) {
		return customerrepo.existsByUsername(username);
	}
	
	public  boolean existsByPassword(String password) {
		return customerrepo.existsByPassword(password);
	}

	public boolean existsById(int id) {
		return customerrepo.existsById(id);
	}

	public Customer save(Customer newuser) {
		return customerrepo.save(newuser);
	}

	public Customer findByUsername(String username) {
		return customerrepo.findByUsername(username);
	}

	public Optional<Customer> findById(Integer id) {
		return customerrepo.findById(id);
	}

	public Account dtotoAccount(CustomerDto user) {
		Account account=new Account();
		account.setAccountType(user.getAccountType());
		account.setBalance(user.getAmount());
		account.setBranch(user.getBranch());
		return account;
	}
	
	public Customer dtoToCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setAddress(customerDto.getAddress());
		customer.setAge(customerDto.getAge());
		customer.setDateOfBirth(customerDto.getDateOfBirth());
		customer.setEmail(customerDto.getEmail());
		customer.setPhone(customerDto.getPhone());
		return customer;
	}

	public long generateAccountNumber() {
		SecureRandom random = new SecureRandom();
		return Math.round(random.nextFloat() * Math.pow(10, 12));
	}

	public String generatePassword() {
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = "abcdefghijklmnopqrstuvwxyz";
		String number = "0123456789";
		String specialchar = "!@#$%^&*?{}()";
		String combination = upper + lower + number + specialchar;
		int length = 8;
		char[] password = new char[length];
		SecureRandom sr = new SecureRandom();
		for (int i = 0; i < length; i++) {
			password[i] = combination.charAt(sr.nextInt(combination.length()));
		}
		return new String(password); 
	}

	public static String generateUserId(String name) {
		SecureRandom random = new SecureRandom();
		name = name.replaceAll("\\s", "");
		System.out.println(name);
		return name.substring(0, 4) + String.valueOf(random.nextInt(10000));
	}

	public ResponseEntity<CustomerDetail> getCustomerDetail(String id)  {
		if (!(existsByUsername(id)))
			throw new CustomerDoesNotExistException("Customer does not exist! please register");

		Customer customer = findByUsername(id);
		if (!(customer.getIsLogined().equals(tr)))
			throw new UserNotloggedInException("User is not Logged in");
		
		return new ResponseEntity<>(new CustomerDetail(200, "Below is the customer detail",customer), HttpStatus.OK);
	}

	public ResponseEntity<Response> loginCustomer(LoginDto login) {
		if (!(existsByUsername(login.getUserId()) && existsByPassword(login.getPassword())))
			throw new UsernamePasswordnotExistException("incorrect username or password. Please provide correct username and password");
			
		Customer customer =findByUsername(login.getUserId());
		if (!(customer.getIsLogined().equals(fls)))
			throw new UserLoggedInAlreadyException("User already Logged in");
		
		customer.setIsLogined(tr);
		save(customer);
		return new ResponseEntity<>(new Response(200, "User logged-in successfully"), HttpStatus.OK);
	}

	public ResponseEntity<Response> customerSignOut(String id) {
		if (!(existsByUsername(id)))
			throw new CustomerDoesNotExistException("Customer does not exist! please register");
			
		Customer customer = findByUsername(id);
		if (!(customer.getIsLogined().equals(tr)))
			throw new UserNotloggedInException("User is not Logged in");
		
		customer.setIsLogined(fls);
		save(customer);
		return new ResponseEntity<>(new Response(200, "User logged out successfully"), HttpStatus.OK);
	}

	public ResponseEntity<RegisterResponse> customerSignUp(CustomerDto customer) {
		if (existsByEmail(customer.getEmail())) {
			throw new UserRegisteredAlreadyException("User already registered");
		}
		if (!(customer.getAccountType().equals("saving") || customer.getAccountType().equals("current")))
			throw new SavingOrCurrentAccountException("account type should be saving or current");
			
		if (customer.getAmount() <= 0)
			throw new AmountMustGreaterThanZeroException("amount must be greater than 0");
//		try {
			Customer newuser = dtoToCustomer(customer);
			long accnu = generateAccountNumber();
			Account account=new Account(); 
			account.setAccountType(customer.getAccountType());
			account.setBalance(customer.getAmount());
			account.setBranch(customer.getBranch());
			account.setAccnumber(accnu);
			newuser.setAccount(account);
			account.setCustomer(newuser);
			newuser.setUsername(generateUserId(newuser.getCustomerName()));
			newuser.setPassword(generatePassword());
			newuser.setIsLogined(fls);
			newuser.setAccount(account);
			save(newuser);
			Credential cr = new Credential(newuser.getUsername(), newuser.getPassword());
			return new ResponseEntity<>(new RegisterResponse(200,"User registered successully and below is the login credential", cr), HttpStatus.OK);

	}

//	public User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setUsername(userDto.getUsername());
//		user.setAddress(userDto.getAddress());
//		user.setAge(userDto.getAge());
//		user.setDateOfBirth(userDto.getDateOfBirth());
//		user.setEmail(userDto.getEmail());
//		user.setPhone(userDto.getPhone());
//		return user; 
//	}
//	
//	public ResponseEntity<?> getUser(DetailsDto detail) {
//		if(!(existsByEmail(detail.getEmail()))) 
//			return new ResponseEntity<>(new Response(400, "User does not exist! please register"),HttpStatus.BAD_REQUEST);
//		List<Object> obList=new ArrayList<>();
//		User byEmail = findByEmail(detail.getEmail());
//		Account account=accountService.findByAccnumber(byEmail.getAccnumber());
//		obList.add(byEmail);
//		obList.add(account);
//		if (!(byEmail.getIsLogined().equals(tr)))
//			return new ResponseEntity<>(new Response(400, "User is not Logged in"), HttpStatus.BAD_REQUEST);
//		return new ResponseEntity<>(obList, HttpStatus.OK);
//		
//	}

	public ResponseEntity<?> register(CustomerDto customer) {
		if (existsByEmail(customer.getEmail()))
			return new ResponseEntity<>(new Response(400, "Customer already registered"), HttpStatus.BAD_REQUEST);
		try {
			Customer newcustomer=dtoToCustomer(customer);
			Account newAccount=newcustomer.getAccount();
			long accnu=generateAccountNumber();
			newAccount.setAccnumber(accnu);
			newcustomer.setIsLogined(fls);
			if(!(customer.getAccountType().equals("saving") || customer.getAccountType().equals("current")))
				return new ResponseEntity<>(new Response(400, "account type should be saving or current"), HttpStatus.BAD_REQUEST);
			if(customer.getAmount()<=0)
				return new ResponseEntity<>(new Response(400, "amount must be greater than 0"), HttpStatus.BAD_REQUEST);
			newcustomer.setUsername(generateUserId(customer.getCustomerName()));
			newcustomer.setPassword(generatePassword());
			save(newcustomer); 
			//accountService.save(newAccount);
			Credential cr=new Credential(newcustomer.getUsername(), newcustomer.getPassword());
			return new ResponseEntity<>(new RegisterResponse(200, "User registered successully and below is the login credential", cr), HttpStatus.OK);
		}
		catch(Exception e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(new Response(400, "User not registered"), HttpStatus.BAD_REQUEST);
	}
}

	public ResponseEntity<?> login(LoginDto login) {
		if (!(existsByUsername(login.getUserId())))
			return new ResponseEntity<>(new Response(400, "incorrect username. Please provide correct username"),HttpStatus.BAD_REQUEST);
		Customer customer = findByUsername(login.getUserId());
		if(!(customer.getIsLogined().equals(fls)))
			return new ResponseEntity<>(new Response(400, "User already Logged in"), HttpStatus.BAD_REQUEST);	
		if(customer.getPassword()!=login.getPassword())
			return new ResponseEntity<>(new Response(400, "incorrect password"), HttpStatus.BAD_REQUEST);
		customer.setIsLogined(tr);
		save(customer);
		return new ResponseEntity<>(new Response(200, "User logged-in successfully"), HttpStatus.OK);
	}

//	public ResponseEntity<?> logout(LogoutDto logout) {
//		if(! (existsByEmail(logout.getEmail())))
//			return new ResponseEntity<>(new Response(400, "User does not exist! please register"),HttpStatus.BAD_REQUEST);
//		User byEmail = findByEmail(logout.getEmail());
//		if (!(byEmail.getIsLogined().equals(tr)))
//			return new ResponseEntity<>(new Response(400, "User is not Logged in"), HttpStatus.BAD_REQUEST);
//		byEmail.setIsLogined(fls);
//		save(byEmail);
//		return new ResponseEntity<>(new Response(200, "User logged out successfully"), HttpStatus.OK);
//	}
	
}
