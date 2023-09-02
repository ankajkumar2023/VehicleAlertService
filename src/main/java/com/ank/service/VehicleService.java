package com.ank.service;

import com.ank.model.VehicleModel;

public interface VehicleService {
	
	public VehicleModel getVehicleByVehicleIdAndUserId(Long vehicleId,Long UserId);
	
	public Long saveVehicle(VehicleModel vehicleModel);
	
	public void updateVehicle(VehicleModel vehicleModel);
	
	public void deleteVehicleByVehicleIdAndUserId(Long id,Long userId);

}
