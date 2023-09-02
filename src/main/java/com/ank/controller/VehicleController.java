package com.ank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.ank.model.VehicleModel;
import com.ank.security.JwtToken;
import com.ank.service.VehicleService;
import com.ank.valitaors.LoginValidation;

@RestController
@CrossOrigin("*")
@RequestMapping("/ank/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private LoginValidation loginValidation;
	
	@Autowired
	private JwtToken jwtToken;

	@GetMapping(value = "/{id}")
	public VehicleModel getVehicle(HttpServletRequest request,@PathVariable Long id) {
		
		Long refNumber =jwtToken.getUserRefNumberFromToken(request.getHeader("Authorization").substring(7));
		return vehicleService.getVehicleByVehicleIdAndUserId(id,refNumber);
	}
	
	
	
	@PostMapping(value = "/save")
	public ResponseWrapper saveVehicle(HttpServletRequest request,@RequestBody VehicleModel vehicleModel) {
		
		validateUserSession(request, vehicleModel.getUserId());
	
		Long id =vehicleService.saveVehicle(vehicleModel);
		ResponseWrapper response = new ResponseWrapper();
		response.setId(id);
		response.setMessage(VehicleAlertLiterals.VEHICLE_SAVE_SUCCESS_MESSAGE);
		return response;
	}
	
	@PutMapping(value = "/update")
	public ResponseWrapper updateVehicle(HttpServletRequest request,@RequestBody VehicleModel vehicleModel) {
		
		validateUserSession(request, vehicleModel.getUserId());
		
		vehicleService.saveVehicle(vehicleModel);
		ResponseWrapper response = new ResponseWrapper();
		response.setId(vehicleModel.getVehicleId());
		response.setMessage(VehicleAlertLiterals.VEHICLE_SAVE_SUCCESS_MESSAGE);
		return response;
	}
			
	@DeleteMapping(value = "/delete/{id}")
	public ResponseWrapper deleteVehicle(HttpServletRequest request,@PathVariable Long id) {
		
		Long refNumber =jwtToken.getUserRefNumberFromToken(request.getHeader("Authorization").substring(7));
		vehicleService.deleteVehicleByVehicleIdAndUserId(id,refNumber);
		ResponseWrapper response = new ResponseWrapper();
		response.setId(id);
		response.setMessage(VehicleAlertLiterals.VEHICLE_DELETE_SUCCESS_MESSAGE);
		return response;
	}
	
	private void validateUserSession(HttpServletRequest request, Long id) {
		if(!loginValidation.isSameSesionOperation(request.getHeader("Authorization"),id)) {
			throw new VehicleAlertException(VehicleAlertLiterals.INVALID_TOKEN_CODE,VehicleAlertLiterals.INVALID_TOKEN_MSG);
		};
	}
}
