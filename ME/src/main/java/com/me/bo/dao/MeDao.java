package com.me.bo.dao;

import java.util.HashMap;
import java.util.List;

import com.me.bo.dto.LoginDTO;
import com.me.bo.dto.TransactionDto;
import com.me.bo.dto.UserDetailsDto;

public interface MeDao {
	
	public Long authenticateUser(LoginDTO loginDto);
	public UserDetailsDto getUserDetails(UserDetailsDto userDetailsDto);
	public int registerUser(HashMap<String,Object> registerUserMap);
	public int addTransaction(HashMap<String, Object> newTransactionMap);
	public List<TransactionDto> getTransaction(TransactionDto transactionDto);


}
