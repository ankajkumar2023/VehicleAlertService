package com.ank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ank.model.LoginModel;
import com.ank.model.TokenModel;
import com.ank.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
		
	@RequestMapping(value = "/ank/getToken", method = RequestMethod.POST)
	public ResponseEntity<?> getAuthenticationToken(@RequestBody LoginModel loginModel) throws Exception {

		final TokenModel token = loginService.getToken(loginModel);
		return ResponseEntity.ok(token);
	}
	
	
	
}
