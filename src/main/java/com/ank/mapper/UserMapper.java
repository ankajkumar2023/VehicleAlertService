package com.ank.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.domain.User;
import com.ank.model.UserModel;
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
		
		mapperFactory.classMap(UserModel.class, User.class).byDefault().customize(new CustomMapper<UserModel, User>() {
            @Override
            public void mapAtoB(UserModel a, User b, MappingContext context) {
            	
            	b.setModifiedDate(VehicleAlertUtil.getCurrentTime());
                if(a.getCreateDate() ==null) {
            		b.setCreateDate(VehicleAlertUtil.getCurrentTime());
               }
            }    
        }).register();
		BoundMapperFacade<UserModel, User> mapper= mapperFactory.getMapperFacade(UserModel.class, User.class);
		User userDomain = new User();
		mapper.map(userModel, userDomain);
		return userDomain;
		
	}	
	
	
	public UserModel mapDomainToModel(User userDomain) {
		
		 mapperFactory.classMap(User.class, UserModel.class).byDefault().register();
		 BoundMapperFacade<User, UserModel> mapper= mapperFactory.getMapperFacade(User.class, UserModel.class);
		 UserModel userModel = new UserModel();
		 mapper.map(userDomain, userModel);
		 return userModel;
		
	}	
	
}
