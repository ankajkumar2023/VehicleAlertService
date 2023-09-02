package com.ank.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		.field("userId", "user.userId").byDefault().customize(new CustomMapper<VehicleModel, Vehicle>() {
            @Override
            public void mapAtoB(VehicleModel a, Vehicle b, MappingContext context) {
            	
            	b.setModifiedDate(VehicleAlertUtil.getCurrentTime());
                if(a.getCreateDate() ==null) {
            		b.setCreateDate(VehicleAlertUtil.getCurrentTime());
               }
            }    
        }).register();
		BoundMapperFacade<VehicleModel, Vehicle> mapper= mapperFactory.getMapperFacade(VehicleModel.class, Vehicle.class);
		Vehicle vehicleDomain = new Vehicle();
		mapper.map(vehicleModel, vehicleDomain);
		return vehicleDomain;
		
	}		
	
	public VehicleModel mapDomainToModel(Vehicle vehicleDomain) {
		
		 mapperFactory.classMap(Vehicle.class, VehicleModel.class)
		 .field("user.userId", "userId").byDefault().register();
		 BoundMapperFacade<Vehicle, VehicleModel> mapper= mapperFactory.getMapperFacade(Vehicle.class, VehicleModel.class);
		 VehicleModel userModel = new VehicleModel();
		 mapper.map(vehicleDomain, userModel);
		 return userModel;
	}	
	
}
