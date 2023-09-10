package com.ank.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.domain.User;
import com.ank.domain.Vehicle;
import com.ank.model.VehicleModel;
import com.ank.util.VehicleAlertUtil;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

@Service
public class VehicleMapper {

	@Autowired
	MapperFactory mapperFactory;
	
	public Vehicle mapModelToDomain(VehicleModel vehicleModel) {
		
		mapperFactory.classMap(VehicleModel.class, Vehicle.class)
		.byDefault().register();
		BoundMapperFacade<VehicleModel, Vehicle> mapper= mapperFactory.getMapperFacade(VehicleModel.class, Vehicle.class);
		Vehicle vehicleDomain = new Vehicle();
		User user = new  User();
		mapper.map(vehicleModel, vehicleDomain);
		user.setUserId(vehicleModel.getUserId());
		vehicleDomain.setUser(user);
		vehicleDomain.setModifiedDate(VehicleAlertUtil.getCurrentTime());
        if(vehicleModel.getCreateDate() ==null) {
        	vehicleDomain.setCreateDate(VehicleAlertUtil.getCurrentTime());
       }
		return vehicleDomain;
		
	}		
	
	public VehicleModel mapDomainToModel(Vehicle vehicleDomain) {
		
		 mapperFactory.classMap(Vehicle.class, VehicleModel.class).byDefault().register();
		 BoundMapperFacade<Vehicle, VehicleModel> mapper= mapperFactory.getMapperFacade(Vehicle.class, VehicleModel.class);
		 VehicleModel userModel = new VehicleModel();
		 mapper.map(vehicleDomain, userModel);
		 userModel.setUserId(vehicleDomain.getUser().getUserId());
		  return userModel;
	}	
	
}
