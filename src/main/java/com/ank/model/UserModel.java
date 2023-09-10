package com.ank.model;

import java.util.Calendar;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserModel {
	
	private Long userId;
	
	@NotNull
	@Size(max = 8,min=5, message = "Name should have between 5 to 8 characters")
	private String firstName;
	
	@Size(max = 8,min=5, message = "Name should have at least 8 characters")
	private String lastName;
	
	@Email
	private String emailId;
	
	@NotNull
	@Size(max = 10,min=10, message = "Mobile Number should have at least 10 digit")
	private String mobile;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String status;
	
	private Calendar createDate;
	
	private Calendar modifiedDate;
	
	@NotNull
	@Size(min=6, message = "Password should have at least 6 characters")
	private String password;
	
	private Set<VehicleModel> vehicle;
			
	public Set<VehicleModel> getVehicle() {
		return vehicle;
	}
	public void setVehicle(Set<VehicleModel> vehicle) {
		this.vehicle = vehicle;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	public Calendar getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
