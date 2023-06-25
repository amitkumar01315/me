package com.me.bo.dto;

public class KeyValueDto {
	
	/**
	 * @param id
	 * @param value
	 */
	public KeyValueDto() {
		  // Default constructor
		}

	
	public KeyValueDto(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	private Long id;
	private String value;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "KeyValueDto [id=" + id + ", value=" + value + "]";
	}
	
	
}
