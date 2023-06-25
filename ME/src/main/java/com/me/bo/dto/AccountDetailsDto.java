package com.me.bo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountDetailsDto {

	private Long accountId;
	private Long userId;
	private String accountName;
	private String accountType;
	private LocalDateTime createdDate;
	private BigDecimal balance;
	private LocalDateTime lastTransactionDate;
	private int isIncludedInTotal;
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public LocalDateTime getLastTransactionDate() {
		return lastTransactionDate;
	}
	public void setLastTransactionDate(LocalDateTime lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}
	public int getIsIncludedInTotal() {
		return isIncludedInTotal;
	}
	public void setIsIncludedInTotal(int isIncludedInTotal) {
		this.isIncludedInTotal = isIncludedInTotal;
	}
	

}
