package com.ank.valitaors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ank.security.JwtToken;

@Component
public class LoginValidation {
	@Autowired
	private JwtToken jwtToken;
	
	public boolean isSameSesionOperation( String requestTokenHeader,Long id) {
		
		if(requestTokenHeader ==null || !requestTokenHeader.startsWith("Bearer ")) {
			return false;
		}
		
		return id.equals(jwtToken.getUserRefNumberFromToken(requestTokenHeader.substring(7)));
		
	}
	
  public boolean isSameSesionOperation(String requestTokenHeader,String userName) {
		
		if(requestTokenHeader ==null || !requestTokenHeader.startsWith("Bearer ")) {
			return false;
		}
		
		return userName.equalsIgnoreCase(jwtToken.getUserNameFromToken(requestTokenHeader.substring(7)));
		
	}

}
