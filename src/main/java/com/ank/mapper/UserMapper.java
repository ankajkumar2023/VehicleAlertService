package com.ank.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.domain.User;
import com.ank.model.UserModel;
import com.ank.model.VehicleModel;
import com.ank.util.VehicleAlertUtil;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

@Service
public class UserMapper {

	@Autowired
	MapperFactory mapperFactory;
	
		
	public  User mapModelToDomain(UserModel userModel) {
		
		mapperFactory.classMap(UserModel.class, User.class).byDefault().register();
		BoundMapperFacade<UserModel, User> mapper= mapperFactory.getMapperFacade(UserModel.class, User.class);
		User userDomain = new User();
		userDomain.setModifiedDate(VehicleAlertUtil.getCurrentTime());
        if(userModel.getCreateDate() ==null) {
        	userDomain.setCreateDate(VehicleAlertUtil.getCurrentTime());
       }
		
		mapper.map(userModel, userDomain);
	
		return userDomain;
		
	}	
	
	
	public UserModel mapDomainToModel(User userDomain) {
		
		 mapperFactory.classMap(User.class, UserModel.class).byDefault().register();
		 BoundMapperFacade<User, UserModel> mapper= mapperFactory.getMapperFacade(User.class, UserModel.class);
		 UserModel userModel = new UserModel();
		 mapper.map(userDomain, userModel);
		 if(userModel.getVehicle() !=null && !userModel.getVehicle().isEmpty()) {
			 for (VehicleModel vm : userModel.getVehicle()) {
				vm.setUserId(userDomain.getUserId());
			}
		 }
		 return userModel;
		
	}	
	
}
