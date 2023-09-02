package com.ank.model;

import java.util.Calendar;

public class TokenModel {
		
	private Calendar date;
	
	private String token;
	
	private Long id;
		
	

	public TokenModel(Calendar date, String token, Long id) {
		super();
		this.date = date;
		this.token = token;
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
