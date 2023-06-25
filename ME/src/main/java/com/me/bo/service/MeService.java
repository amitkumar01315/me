package com.me.bo.service;

import java.util.List;

import com.me.bo.dto.LoginDTO;
import com.me.bo.dto.TransactionDto;
import com.me.bo.dto.UserDetailsDto;

public interface MeService {
	
	public Long authenticateUser(LoginDTO loginDto);
	public UserDetailsDto getUserDetails(UserDetailsDto userDetailsDto);
	public int registerUser(UserDetailsDto userDetailsDto);
	public int addTransaction(TransactionDto transactionDto);
	public List<TransactionDto> getTransaction(TransactionDto transactionDto);

	

}
