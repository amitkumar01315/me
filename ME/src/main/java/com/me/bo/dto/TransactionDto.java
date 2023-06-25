package com.me.bo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionDto {
	private String transactionId;
	private String userId;
	private String accountId;	
	private String accountName;		
	private LocalDateTime date;
	private BigDecimal amount;	
	private KeyValueDto type;
	private KeyValueDto subCategory;
	private KeyValueDto category;
	private KeyValueDto paymentMethod;
	private String merchant;
	private String description;
	private KeyValueDto paymentStatus;
	private KeyValueDto currency;
	private String receiptUrl;
	private String tags;
	private Integer isActive;
	private BigDecimal fromAmount;	
	private BigDecimal toAmount;	
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private LocalDateTime crtDt;
	private LocalDateTime lstUpdtDt;



	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public KeyValueDto getType() {
		return type;
	}
	public void setType(KeyValueDto type) {
		this.type = type;
	}
	public KeyValueDto getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(KeyValueDto subCategory) {
		this.subCategory = subCategory;
	}
	public KeyValueDto getCategory() {
		return category;
	}
	public void setCategory(KeyValueDto category) {
		this.category = category;
	}
	public KeyValueDto getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(KeyValueDto paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public KeyValueDto getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(KeyValueDto paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public KeyValueDto getCurrency() {
		return currency;
	}
	public void setCurrency(KeyValueDto currency) {
		this.currency = currency;
	}
	public String getReceiptUrl() {
		return receiptUrl;
	}
	public void setReceiptUrl(String receiptUrl) {
		this.receiptUrl = receiptUrl;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public BigDecimal getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}
	public BigDecimal getToAmount() {
		return toAmount;
	}
	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDateTime getToDate() {
		return toDate;
	}
	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	public LocalDateTime getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(LocalDateTime crtDt) {
		this.crtDt = crtDt;
	}
	public LocalDateTime getLstUpdtDt() {
		return lstUpdtDt;
	}
	public void setLstUpdtDt(LocalDateTime lstUpdtDt) {
		this.lstUpdtDt = lstUpdtDt;
	}
	@Override
	public String toString() {
		return "TransactionDto [transactionId=" + transactionId + ", userId=" + userId + ", accountId=" + accountId
				+ ", accountName=" + accountName + ", date=" + date + ", amount=" + amount + ", type=" + type
				+ ", subCategory=" + subCategory + ", category=" + category + ", paymentMethod=" + paymentMethod
				+ ", merchant=" + merchant + ", description=" + description + ", paymentStatus=" + paymentStatus
				+ ", currency=" + currency + ", receiptUrl=" + receiptUrl + ", tags=" + tags + ", isActive=" + isActive
				+ ", fromAmount=" + fromAmount + ", toAmount=" + toAmount + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", crtDt=" + crtDt + ", lstUpdtDt=" + lstUpdtDt + "]";
	}
	
	
}