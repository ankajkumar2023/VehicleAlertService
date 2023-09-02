package com.ank.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.domain.Login;
import com.ank.domain.Role;
import com.ank.model.LoginModel;
import com.ank.model.UserModel;
import com.ank.util.VehicleAlertUtil;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

@Service
public class LoginMapper {

	
	@Autowired
	MapperFactory mapperFactory;
	
		
	public  Login mapModelToDomain(LoginModel loginModel) {
		
		mapperFactory.classMap(LoginModel.class, Login.class).byDefault().register();
		BoundMapperFacade<LoginModel, Login> mapper= mapperFactory.getMapperFacade(LoginModel.class, Login.class);
		Login loginDomain = new Login();
		mapper.map(loginModel, loginDomain);
		return loginDomain;
		
	}	
	
public  Login mapUserModelToLoginDomain(UserModel userModel) {
		
		mapperFactory.classMap(UserModel.class, Login.class)
		.field("firstName", "userName")
		.field("password", "password")
		.field("createDate", "createDate")
		.byDefault().customize(new CustomMapper<UserModel, Login>() {
            @Override
            public void mapAtoB(UserModel a, Login b, MappingContext context) {
            	
            	Set<Role> roles = new HashSet<>();
        		Role role= new Role();
        		role.setRole("USER");
        		roles.add(role);
                b.setRoles(roles);
                b.setModifiedDate(VehicleAlertUtil.getCurrentTime());
                role.setModifiedDate(VehicleAlertUtil.getCurrentTime());
             
                if(a.getCreateDate() ==null) {
            		b.setCreateDate(VehicleAlertUtil.getCurrentTime());
            		role.setCreateDate(VehicleAlertUtil.getCurrentTime());
            	}else {
            		role.setCreateDate(a.getCreateDate());
            	}
            }
        }).register();
		 BoundMapperFacade<UserModel, Login> mapper= mapperFactory.getMapperFacade(UserModel.class, Login.class);
		 Login loginDomain = new Login();
		 mapper.map(userModel, loginDomain);
		 return loginDomain;
		
	}	
	
	
 		
	
	public LoginModel mapDomainToModel(Login loginDomain) {
		
		mapperFactory.classMap(Login.class, LoginModel.class).byDefault().register();
		 BoundMapperFacade<Login, LoginModel> mapper= mapperFactory.getMapperFacade(Login.class, LoginModel.class);
		 LoginModel loginModel = new LoginModel();
		 mapper.map(loginDomain, loginModel);
		 return loginModel;
		
	}	
}
