package com.ank.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ank.domain.Login;
import com.ank.exception.VehicleAlertException;
import com.ank.literals.VehicleAlertLiterals;
import com.ank.model.LoginModel;
import com.ank.model.TokenModel;
import com.ank.repository.LoginRepository;
import com.ank.security.JwtToken;
import com.ank.service.LoginService;
import com.ank.util.VehicleAlertUtil;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private JwtToken jwtToken;

	@Override
	public TokenModel getToken(LoginModel loginModel) {

		Login loginDomain = loginRepository.getLoginUserDetailByUserAndPassword(loginModel.getUser(),loginModel.getPassword());

		if (loginDomain == null) {

			throw new VehicleAlertException(VehicleAlertLiterals.INVALID_USER_CODE,VehicleAlertLiterals.INVALID_USER_MSG);
		}
		
		List<GrantedAuthority> roles = loginDomain.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		final UserDetails userDetails = new User(loginDomain.getUserName(), loginDomain.getPassword(),roles);
		return new TokenModel(VehicleAlertUtil.getCurrentTime(),jwtToken.generateToken(userDetails, loginDomain.getLoginId()),loginDomain.getUserId());

	}

	@Override
	public boolean isValidUser(Long id, String userName) {
		Login loginDomain = loginRepository.getLoginUserDetailByByIdAndUser(id, userName);
		if (loginDomain != null) {
			return true;
		}
		return false;
	}

}
