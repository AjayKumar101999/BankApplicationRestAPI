package com.application.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.application.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<Map<String ,String>> handleNotvalidEception(MethodArgumentNotValidException ex) {
		Map<String, String> map=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
		 String field = ((FieldError)error).getField();
		 String message = error.getDefaultMessage();  
		 
		 map.put(field, message);
			
		});
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST); 
	} 
	 
	 @ExceptionHandler(HttpMessageNotReadableException.class)
	 public ResponseEntity<Response> notReadablEntity(HttpMessageNotReadableException ex) {
		return new ResponseEntity<>( new Response(400, "Json format does not looks good. Please correct!"), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(AccountDoesnotExistException.class)
	 public ResponseEntity<Response> accountDoesnotExistException(AccountDoesnotExistException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(AmountMustGreaterThanZeroException.class)
	 public ResponseEntity<Response> amountMustGreaterThanZeroException(AmountMustGreaterThanZeroException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(AmountNotTransferredException.class)
	 public ResponseEntity<Response> amountNotTransferredException(AmountNotTransferredException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(BeneficiaryAddedAlreadyException.class)
	 public ResponseEntity<Response> beneficiaryAddedAlreadyException(BeneficiaryAddedAlreadyException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(InsufficiantAmountException.class)
	 public ResponseEntity<Response> insufficiantAmountException(InsufficiantAmountException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(SavingOrCurrentAccountException.class)
	 public ResponseEntity<Response> savingOrCurrentAccountException(SavingOrCurrentAccountException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(UserLoggedInAlreadyException.class)
	 public ResponseEntity<Response> userLoggedInAlreadyException(UserLoggedInAlreadyException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(UsernamePasswordnotExistException.class)
	 public ResponseEntity<Response> usernamePasswordnotExistException(UsernamePasswordnotExistException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(UserNotRegisteredException.class)
	 public ResponseEntity<Response> userNotRegisteredException(UserNotRegisteredException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(CustomerDoesNotExistException.class)
	 public ResponseEntity<Response> customerDoesNotExistException(CustomerDoesNotExistException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(UserRegisteredAlreadyException.class)
	 public ResponseEntity<Response> userRegisteredAlreadyException(UserRegisteredAlreadyException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(UserNotloggedInException.class)
	 public ResponseEntity<Response> userNotloggedInException(UserNotloggedInException ex) {
		return new ResponseEntity<>( new Response(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	 }
	 

	 
}
