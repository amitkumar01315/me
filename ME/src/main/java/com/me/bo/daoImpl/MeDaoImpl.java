package com.me.bo.daoImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.me.bo.dao.MeDao;
import com.me.bo.dto.KeyValueDto;
import com.me.bo.dto.LoginDTO;
import com.me.bo.dto.TransactionDto;
import com.me.bo.dto.UserDetailsDto;
import com.me.bo.utility.MeSqlUtility;
import com.me.bo.utility.MeUtility;

@Repository
public class MeDaoImpl implements MeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Long authenticateUser(LoginDTO loginDto) {
		String Query = "SELECT COUNT(*) AS CNT FROM `me_user_mst` WHERE USER_NAME= '" + loginDto.getUserName()
				+ "'   AND  PASSWORD= '" + loginDto.getUserPassword() + "'";

		return jdbcTemplate.queryForObject(Query, Long.class);
	}

	public UserDetailsDto getUserDetails(UserDetailsDto userDetailsDto) {
		String Query = "SELECT * FROM `me_user_mst` WHERE USER_NAME = '" + userDetailsDto.getUserName() + "'";

		return jdbcTemplate.query(Query, new BeanPropertyRowMapper<UserDetailsDto>(UserDetailsDto.class)).get(0);

	}

	public int registerUser(HashMap<String, Object> registerUserMap) {
		return MeSqlUtility.insertOneRow(jdbcTemplate, "me_user_mst", registerUserMap);

	}

	@Override
	public int addTransaction(HashMap<String, Object> newTransactionMap) {
		return MeSqlUtility.insertOneRow(jdbcTemplate, "me_transaction_dtls", newTransactionMap);
	}

	@Override
	public List<TransactionDto> getTransaction(TransactionDto transactionDto) {
		List<TransactionDto> transactionDtoLst=null;
		HashMap<String, Object> valueMap;
		StringBuilder query = new StringBuilder(
				" SELECT USER_ID, TRANSACTION_ID,ACCOUNT_ID,(SELECT ACCOUNT_NAME FROM `me_account_dtls` WHERE ACCOUNT_ID=trn.ACCOUNT_ID) AS ACCOUNT_NAME,DATE_FORMAT(TRANSACTION_DATE,'%Y/%m/%d %T') AS TRANSACTION_DATE, AMOUNT, TYPE , CATEGORY , (SELECT CATEGORY_NAME FROM `me_category_dtls` WHERE CATEGORY_ID=trn.CATEGORY) AS CATEGORY_NAME , SUB_CATEGORY,(SELECT SUB_CATEGORY_NAME FROM `me_sub_category_dtls` WHERE SUB_CATEGORY_ID=trn.SUB_CATEGORY ) AS SUB_CATEGORY_NAME ,PAYMENT_METHOD , (SELECT PM_NAME FROM `me_payment_method` WHERE PM_ID=trn.PAYMENT_METHOD) AS PAYMENT_METHOD_NAME ,MERCHANT, DESCRIPTION, PAYMENT_STATUS, CURRENCY ,(SELECT CURR_NAME FROM `me_currency_dtls` WHERE CURR_ID=trn.CURRENCY) AS CURR_NAME ,RECEIPT_URL, TAGS ,  DATE_FORMAT(CRT_DT,'%Y/%m/%d %T') AS CRT_DT,IS_ACTIVE,   DATE_FORMAT(LST_UPDT_DT,'%Y/%m/%d %T') AS LST_UPDT_DT FROM `me_transaction_dtls`trn  WHERE USER_ID='"
						+ transactionDto.getUserId() + "'");

		if (transactionDto.getAccountId() != null) {
			query.append(" AND ACCOUNT_ID='" + transactionDto.getAccountId() + "' ");
		}

		if (transactionDto.getCategory()!= null && transactionDto.getCategory().getId() != null) {
			query.append(" AND CATEGORY='" + transactionDto.getCategory().getId() + "' ");
		}

		if (transactionDto.getSubCategory()!= null && transactionDto.getSubCategory().getId() != null) {
			query.append(" AND SUB_CATEGORY='" + transactionDto.getSubCategory().getId() + "' ");
		}

		if (transactionDto.getIsActive() != null) {
			query.append(" AND IS_ACTIVE=" + transactionDto.getIsActive() + " ");
		}

		if (transactionDto.getType() != null) {
			query.append(" AND TYPE='" + transactionDto.getType().getId() + "' ");
		}

		if (transactionDto.getFromAmount() != null && transactionDto.getToAmount() != null) {
			query.append(
					" AND AMOUNT BETWEEN " + transactionDto.getFromAmount() + " AND " + transactionDto.getToAmount());
		}

		if (transactionDto.getTags() != null) {
			query.append(" AND TAGS LIKE '%" + transactionDto.getTags() + "%' ");
		}

		if (transactionDto.getFromDate() != null && transactionDto.getToDate() != null) {
			query.append(" AND TRANSACTION_DATE BETWEEN'" + transactionDto.getFromDate() + "' AND '"
					+ transactionDto.getToDate() + "'");
		}

		List<HashMap<String, Object>> rowMap = MeSqlUtility.executeSelectQuery(jdbcTemplate, query.toString());

		if (rowMap != null) {
			transactionDtoLst = new ArrayList<TransactionDto>();

			for (int i = 0; i < rowMap.size(); i++) {

				TransactionDto transactionTemp = new TransactionDto();
				valueMap = rowMap.get(i);

				if (valueMap != null) {
					
					if (valueMap.get("USER_ID") != null)
						transactionTemp.setUserId(valueMap.get("USER_ID").toString());

					if (valueMap.get("TRANSACTION_ID") != null)
						transactionTemp.setTransactionId(valueMap.get("TRANSACTION_ID").toString());

					if (valueMap.get("ACCOUNT_ID") != null)
						if (valueMap.get("ACCOUNT_NAME") != null) {
							transactionTemp.setAccountId(valueMap.get("ACCOUNT_ID").toString());
							transactionTemp.setAccountName(valueMap.get("ACCOUNT_NAME").toString());
						}

					if (valueMap.get("TRANSACTION_DATE") != null)
						transactionTemp.setDate(
								LocalDateTime.parse(valueMap.get("TRANSACTION_DATE").toString(), MeUtility.yyyy_MM_dd_HH_mm_ss));

					if (valueMap.get("AMOUNT") != null)
						transactionTemp.setAmount((BigDecimal) valueMap.get("AMOUNT"));

					if (valueMap.get("TYPE") != null)
						if (Integer.valueOf(valueMap.get("TYPE").toString()) == 0)
							transactionTemp.setType(new KeyValueDto(0L, "Debit"));
						else
							transactionTemp.setType(new KeyValueDto(1L, "Credit"));

					if (valueMap.get("CATEGORY") != null)
						if (valueMap.get("CATEGORY_NAME") != null)
							transactionTemp
									.setCategory(new KeyValueDto(Long.valueOf(valueMap.get("CATEGORY").toString()),
											valueMap.get("CATEGORY_NAME").toString()));

					if (valueMap.get("SUB_CATEGORY") != null)
						if (valueMap.get("SUB_CATEGORY_NAME") != null)
							transactionTemp.setSubCategory(
									new KeyValueDto(Long.valueOf(valueMap.get("SUB_CATEGORY").toString()),
											valueMap.get("SUB_CATEGORY_NAME").toString()));

					if (valueMap.get("PAYMENT_METHOD") != null)
						if (valueMap.get("PAYMENT_METHOD_NAME") != null)
							transactionTemp.setPaymentMethod(
									new KeyValueDto(Long.valueOf(valueMap.get("PAYMENT_METHOD").toString()),
											valueMap.get("PAYMENT_METHOD_NAME").toString()));

					if (valueMap.get("MERCHANT") != null)
						transactionTemp.setMerchant(valueMap.get("MERCHANT").toString());

					if (valueMap.get("DESCRIPTION") != null)
						transactionTemp.setDescription(valueMap.get("DESCRIPTION").toString());

					if (valueMap.get("PAYMENT_STATUS") != null)
						if (Integer.valueOf(valueMap.get("PAYMENT_STATUS").toString()) == 0)
							transactionTemp.setPaymentStatus(new KeyValueDto(0L, "Failed"));
						else
							transactionTemp.setPaymentStatus(new KeyValueDto(1L, "Success"));

					if (valueMap.get("CURRENCY") != null)
						if (valueMap.get("CURR_NAME") != null)
							transactionTemp
									.setCurrency(new KeyValueDto(Long.valueOf(valueMap.get("CURRENCY").toString()),
											valueMap.get("CURR_NAME").toString()));

					if (valueMap.get("RECEIPT_URL") != null)
						transactionTemp.setReceiptUrl(valueMap.get("RECEIPT_URL").toString());

					if (valueMap.get("TAGS") != null)
						transactionTemp.setTags(valueMap.get("TAGS").toString());

					if (valueMap.get("IS_ACTIVE") != null)
						transactionTemp.setIsActive(Integer.valueOf(valueMap.get("IS_ACTIVE").toString()));

					if (valueMap.get("LST_UPDT_DT") != null)
						transactionTemp.setLstUpdtDt(
								LocalDateTime.parse(valueMap.get("LST_UPDT_DT").toString(), MeUtility.yyyy_MM_dd_HH_mm_ss));

					if (valueMap.get("CRT_DT") != null)
						transactionTemp.setCrtDt(LocalDateTime.parse(valueMap.get("CRT_DT").toString(), MeUtility.yyyy_MM_dd_HH_mm_ss));
					
					transactionDtoLst.add(transactionTemp);
				}

			}

		}

		return transactionDtoLst;
	}

}
