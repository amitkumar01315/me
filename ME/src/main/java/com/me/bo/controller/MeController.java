package com.me.bo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.me.bo.dto.LoginDTO;
import com.me.bo.dto.TransactionDto;
import com.me.bo.dto.UserDetailsDto;
import com.me.bo.service.MeService;

@Controller
public class MeController {
	@Autowired
	MeService meService;

	@RequestMapping("/rest/me/v1/login")
	public ResponseEntity<Object> authenticateUser(@RequestBody LoginDTO loginDto) {
		return new ResponseEntity<Object>(meService.authenticateUser(loginDto), HttpStatus.OK);
	}

	@RequestMapping("/rest/me/v1/userDetails")
	public ResponseEntity<Object> getUserDetails(@RequestBody UserDetailsDto userDetailsDto) {				
		return new ResponseEntity<Object>(meService.getUserDetails(userDetailsDto), HttpStatus.OK);
	}
	
	@RequestMapping("/rest/me/v1/registerUser")
	public ResponseEntity<Object> registerUser(@RequestBody UserDetailsDto userDetailsDto) {	
		return new ResponseEntity<Object>(meService.registerUser(userDetailsDto), HttpStatus.OK);
	}
	
	@RequestMapping("/rest/me/v1/addTransaction")
	public ResponseEntity<Object> addTransaction(@RequestBody TransactionDto transactionDto) {				
		return new ResponseEntity<Object>(meService.addTransaction(transactionDto), HttpStatus.OK);
	}
	
	@RequestMapping("/rest/me/v1/getTransaction")
	public ResponseEntity<Object> getTransaction(@RequestBody TransactionDto transactionDto) {			
		return new ResponseEntity<Object>(meService.getTransaction(transactionDto), HttpStatus.OK);
	}
	
	

	
	
	
	
}
