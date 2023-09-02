package com.ank.service;

import com.ank.model.LoginModel;
import com.ank.model.TokenModel;

public interface LoginService {
	
	public TokenModel getToken(LoginModel loginModel) ;
	
	public boolean isValidUser(Long id,String user);

}
