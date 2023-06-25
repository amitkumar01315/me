package com.me.bo.serviceImpl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.bo.dao.MeDao;
import com.me.bo.dto.LoginDTO;
import com.me.bo.dto.TransactionDto;
import com.me.bo.dto.UserDetailsDto;
import com.me.bo.service.MeService;
import com.me.bo.utility.MeUtility;

@Service
public class MeServiceImpl implements MeService {

	@Autowired
	MeDao meDao;

	public Long authenticateUser(LoginDTO loginDto) {
		return meDao.authenticateUser(loginDto);
	}

	public UserDetailsDto getUserDetails(UserDetailsDto userDetailsDto) {
	return meDao.getUserDetails(userDetailsDto);
	}
	
	public int registerUser(UserDetailsDto userDetailsDto) {
		HashMap<String,Object> registerUserMap=createRegisterUserMap(userDetailsDto);
		return meDao.registerUser(registerUserMap);
	}

	private HashMap<String,Object> createRegisterUserMap(UserDetailsDto userDetailsDto) {
		HashMap<String,Object> registerUserMap= new HashMap<String,Object>();
		registerUserMap.put("USER_ID", 0);
		registerUserMap.put("USER_NAME" ,userDetailsDto.getUserName());
		registerUserMap.put("PASSWORD", userDetailsDto.getPassword());
		registerUserMap.put("F_NAME", userDetailsDto.getfName());
		registerUserMap.put("L_NAME", userDetailsDto.getlName());
		registerUserMap.put("CELL_NO", userDetailsDto.getCellNo());
		registerUserMap.put("EMAIL_ID", userDetailsDto.getEmailId());
		registerUserMap.put("ADDRESS", userDetailsDto.getAddress());
		registerUserMap.put("CITY", userDetailsDto.getCity());
		registerUserMap.put("PINCODE", userDetailsDto.getPincode());
		registerUserMap.put("DISTRICT", userDetailsDto.getDistrict());
		registerUserMap.put("ACTIVE_FLAG", 1);
		registerUserMap.put("VALIDITY_DATE", userDetailsDto.getValidityDate());
		registerUserMap.put("CRT_DT", MeUtility.getDateTime("yyyy/MM/dd HH:mm:ss"));
		registerUserMap.put("LST_UPDT_DT", null);
//		registerUserMap.put("LOGIN_ATTEMPT", userDetailsDto);
//		registerUserMap.put("LOGIN_ATTEMPT_DATE", userDetailsDto);
//		registerUserMap.put("RESET_ATTEMPT", userDetailsDto);
//		registerUserMap.put("RESET_ATTEMPT_DATE", userDetailsDto);
		
		
		return registerUserMap;		
	}

	@Override
	public int addTransaction(TransactionDto transactionDto) {
		HashMap<String,Object> newTransactionMap=createNewTransactionMap(transactionDto);
		return meDao.addTransaction(newTransactionMap);		
	}

	private HashMap<String, Object> createNewTransactionMap(TransactionDto transactionDto) {
		HashMap<String,Object> newTransactionMap = new HashMap<String,Object>();
		newTransactionMap.put("TRANSACTION_ID", 0);
		newTransactionMap.put("USER_ID" ,transactionDto.getUserId());
		newTransactionMap.put("ACCOUNT_ID", transactionDto.getAccountId());
		newTransactionMap.put("TRANSACTION_DATE", transactionDto.getTransactionId());
		newTransactionMap.put("AMOUNT", transactionDto.getAmount());
		newTransactionMap.put("TYPE", transactionDto.getType().getId());
		newTransactionMap.put("CATEGORY", transactionDto.getCategory().getId());
		newTransactionMap.put("SUB_CATEGORY", transactionDto.getSubCategory().getId());
		newTransactionMap.put("PAYMENT_METHOD", transactionDto.getPaymentMethod().getId());
		newTransactionMap.put("MERCHANT", transactionDto.getMerchant());
		newTransactionMap.put("DESCRIPTION", transactionDto.getDescription());
		newTransactionMap.put("PAYMENT_STATUS", transactionDto.getPaymentStatus().getId());
		newTransactionMap.put("CURRENCY", transactionDto.getCurrency().getId());
		newTransactionMap.put("RECEIPT_URL", transactionDto.getReceiptUrl() );
		newTransactionMap.put("TAGS", transactionDto.getTags());
		newTransactionMap.put("CRT_DT", MeUtility.getDateTime("yyyy/MM/dd HH:mm:ss"));
		newTransactionMap.put("IS_ACTIVE", 1);
		return newTransactionMap;
	}

	@Override
	public List<TransactionDto> getTransaction(TransactionDto transactionDto) {
		return meDao.getTransaction(transactionDto);
	}


}
