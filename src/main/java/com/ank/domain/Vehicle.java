package com.ank.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vehicle1")
public class Vehicle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "vehicleId")
	private Long vehicleId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@Column(name = "vehicleNumber")
	private String vehicleNumber;
	@Column(name = "vehicleRC")
	private String vehicleRC;
	@Column(name = "vehicleType")
	private String vehicleType;
	@Column(name = "yearOfRegistration")
	private String yearOfRegistration;
	@Column(name = "ownerName")
	private String ownerName;
	@Column(name = "mobileNumber")
	private String mobileNumber;
	@Column(name = "createDate")
	private Calendar createDate;
	@Column(name = "modifiedDate")
	private Calendar modifiedDate;
	
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVehicleRC() {
		return vehicleRC;
	}
	public void setVehicleRC(String vehicleRC) {
		this.vehicleRC = vehicleRC;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getYearOfRegistration() {
		return yearOfRegistration;
	}
	public void setYearOfRegistration(String yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
    

}
