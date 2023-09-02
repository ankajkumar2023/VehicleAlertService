package com.ank.service;

import com.ank.model.UserModel;

public interface UserService {
	
	public UserModel getUserInfo(Long id,String userName);
	
	public UserModel getUserDetail(Long id,String userName);
	
	public Long saveUserInfo(UserModel userModel);
	
	public void updateUserInfo(UserModel userModel);
	
	public void deleteUserInfo(Long id,String UserName);

}
