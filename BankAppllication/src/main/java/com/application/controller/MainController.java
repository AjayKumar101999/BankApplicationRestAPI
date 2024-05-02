//package com.application.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.application.dtos.DetailsDto;
//import com.application.dtos.LoginDto;
//import com.application.dtos.LogoutDto;
//import com.application.dtos.UserDto;
//import com.application.service.AccountService;
//import com.application.service.CustomerService;
//import com.application.service.UserService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/user")
//public class MainController {
//
//    private final UserService userService;
//    private final CustomerService customerService;
//	
//	 public MainController(UserService userService, CustomerService customerService ) {
//	     this.userService=userService;
//	     this.customerService = customerService;
//	 }
//
//	@PostMapping("/detail")
//	public ResponseEntity<?> getuser(@RequestBody @Valid DetailsDto detail) {
//		return userService.getUser(detail);
//	}
//
//	@PostMapping("/register")
//	public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto user) {
//			return customerService.register(user);
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDto login) {
//		return userService.login(login);
//	}
//	
//	@PostMapping("/logout")
//	public ResponseEntity<?> logoutUser(@RequestBody @Valid LogoutDto logout) {
//		return userService.logout(logout);
//	}
//
//}
