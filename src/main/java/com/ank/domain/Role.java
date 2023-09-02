package com.ank.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name="roleId")
	private Long roleId;
	
//	@Column(name="loginId")
//	private Long loginId;
//	
	@Column(name="role")
	private String role;
	
	@Column(name = "createDate")
	private Calendar createDate;
	
	@Column(name = "modifiedDate")
	private Calendar modifiedDate;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

//	public Long getLoginId() {
//		return loginId;
//	}
//
//	public void setLoginId(Long loginId) {
//		this.loginId = loginId;
//	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
