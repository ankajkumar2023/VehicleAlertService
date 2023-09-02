package com.ank.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.ank.domain.Login;
import com.ank.repository.LoginRepository;

@Service
public class ApiAuthenticationProvider implements AuthenticationProvider  {

	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
		
		Login loginDomain =loginRepository.getLoginUserDetailByUserAndPassword(username,password);
		List<GrantedAuthority> roles=loginDomain.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		
	        if (loginDomain.getUserName().equals(username) && loginDomain.getPassword().equals(password)) {
	            return new UsernamePasswordAuthenticationToken
	              (username, password, roles);
	        } else {
	            throw new 
	              BadCredentialsException("External system authentication failed");
	        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
