package com.ank.serviceimpl;

import static com.ank.literals.VehicleAlertLiterals.VEHICLE_NOT_EXIST_CODE;
import static com.ank.literals.VehicleAlertLiterals.VEHICLE_NOT_EXIST_MSG;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.domain.Vehicle;
import com.ank.exception.VehicleAlertException;
import com.ank.mapper.VehicleMapper;
import com.ank.model.VehicleModel;
import com.ank.repository.VehicleRepository;
import com.ank.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private VehicleMapper vehicleMapper;
	
	@Override
	public VehicleModel getVehicleByVehicleIdAndUserId(Long vehicleId,Long UserId) {
		
		Vehicle vehicleDomain= vehicleRepository.getVehicleByVehicleIdAndUserId(vehicleId,UserId);
		validateVehicleExistInSystem(vehicleDomain);
		return vehicleMapper.mapDomainToModel(vehicleDomain);
	}

	@Override
	public Long saveVehicle(VehicleModel vehicleModel) {
		
		Vehicle vehicleDomain= vehicleMapper.mapModelToDomain(vehicleModel);
		vehicleRepository.save(vehicleDomain);
		return vehicleDomain.getVehicleId();
	}

	@Override
	public void updateVehicle(VehicleModel vehicleModel) {
		Vehicle vehicleDomain= vehicleMapper.mapModelToDomain(vehicleModel);
		vehicleRepository.save(vehicleDomain);
    }

	@Override
	@Transactional
	public void deleteVehicleByVehicleIdAndUserId(Long id,Long userId) {
		vehicleRepository.deleteVehicleByVehicleIdAndUserId(id,userId);
		
	}
	
	private void validateVehicleExistInSystem(Vehicle vehicleDomain) {
		if(vehicleDomain == null) {
			throw new VehicleAlertException(VEHICLE_NOT_EXIST_CODE, VEHICLE_NOT_EXIST_MSG);
		}
	}

}
