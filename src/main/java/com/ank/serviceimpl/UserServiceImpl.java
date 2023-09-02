package com.ank.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.domain.Login;
import com.ank.domain.User;
import com.ank.mapper.LoginMapper;
import com.ank.mapper.UserMapper;
import com.ank.model.UserModel;
import com.ank.repository.LoginRepository;
import com.ank.repository.UserRepository;
import com.ank.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserModel getUserInfo(Long id,String userName) {
		
		User userDomain= userRepository.findById(id).get();
		UserModel userModel =userMapper.mapDomainToModel(userDomain);
		userModel.setVehicle(null);
		return userModel;
	}

	
	@Override
	@Transactional
	public Long saveUserInfo(UserModel userModel) {
		
		User userDomain= userMapper.mapModelToDomain(userModel);
		userRepository.save(userDomain);
		Long userId= userDomain.getUserId();
		Login login =loginMapper.mapUserModelToLoginDomain(userModel);
		login.setUserId(userId);
		loginRepository.save(login);
		return userId;
	}

	

	@Override
	@Transactional
	public void updateUserInfo(UserModel userModel) {
		User userDomain= userMapper.mapModelToDomain(userModel);
		userRepository.save(userDomain);
		
	}

	@Override
	@Transactional
	public void deleteUserInfo(Long id,String userName) {
		userRepository.deleteUserDetailByById(id,userName);
		
	}

	@Override
	public UserModel getUserDetail(Long id, String userName) {
		
		User userDomain= userRepository.findUserById(id);
		return userMapper.mapDomainToModel(userDomain);
	}	
	
}
