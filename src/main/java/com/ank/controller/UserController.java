package com.ank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ank.exception.VehicleAlertException;
import com.ank.literals.VehicleAlertLiterals;
import com.ank.model.ResponseWrapper;
import com.ank.model.UserModel;
import com.ank.security.JwtToken;
import com.ank.service.UserService;
import com.ank.valitaors.LoginValidation;

@RestController
@RequestMapping("/ank")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private LoginValidation loginValidation;
		
	@GetMapping(value = "/user/{id}")
	public UserModel getUserInfo(HttpServletRequest request,@PathVariable Long id) {
	
		validateUserSession(request, id);
		
		String userName =jwtToken.getUserNameFromToken(request.getHeader("Authorization").substring(7));
		
		return userService.getUserInfo(id,userName);
	}
	
	@GetMapping(value = "/userdetail/{id}")
	public UserModel getUserDetails(HttpServletRequest request,@PathVariable Long id) {
	
		validateUserSession(request, id);
		
		String userName =jwtToken.getUserNameFromToken(request.getHeader("Authorization").substring(7));
		
		return userService.getUserDetail(id,userName);
	}
			
	@PostMapping(value = "/user/save")
	public ResponseWrapper saveUserInfo(HttpServletRequest request,@RequestBody UserModel userModel) {

		Long id =userService.saveUserInfo(userModel);
		ResponseWrapper response = new ResponseWrapper();
		response.setId(id);
		response.setMessage(VehicleAlertLiterals.USER_SAVE_SUCCESS_MESSAGE);
		return response;
	}
	
	@PutMapping(value = "/user/update")
	public ResponseWrapper updateUserInfo(HttpServletRequest request,@RequestBody UserModel userModel){
		
		validateUserSession(request, userModel.getUserId());
		
		userService.saveUserInfo(userModel);
		ResponseWrapper response = new ResponseWrapper();
		response.setId(userModel.getUserId());
		response.setMessage(VehicleAlertLiterals.USER_UPDATE_SUCCESS_MESSAGE);
		return response;
	}

			
	@DeleteMapping(value = "/user/delete/{id}")
	public ResponseWrapper deleteUserInfo(HttpServletRequest request,@PathVariable Long id){
		
		validateUserSession(request, id);
		
		String userName =jwtToken.getUserNameFromToken(request.getHeader("Authorization").substring(7));
		
		userService.deleteUserInfo(id,userName);
		ResponseWrapper response = new ResponseWrapper();
		response.setId(id);
		response.setMessage(VehicleAlertLiterals.USER_DELETE_SUCCESS_MESSAGE);
		return response;
	}
	
	private void validateUserSession(HttpServletRequest request, Long id) {
		if(!loginValidation.isSameSesionOperation(request.getHeader("Authorization"),id)) {
			throw new VehicleAlertException(VehicleAlertLiterals.INVALID_TOKEN_CODE,VehicleAlertLiterals.INVALID_TOKEN_MSG);
		};
	}
	
}
