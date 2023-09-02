package com.ank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

	@Configuration
	public static class ApiWebSecurityConfigurerAdapter{

		@Autowired
		private JwtRequestFilter jwtRequestFilter;

		@Autowired
		private ApiAuthenticationProvider apiAuthenticationProvider;
		
		@Autowired
	    AuthenticationEntryPoint authEntryPoint;

		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(apiAuthenticationProvider);
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
			return http.getSharedObject(AuthenticationManagerBuilder.class).build();
		}

		
		@Bean
	    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
			
			httpSecurity.antMatcher("/ank/**").csrf().disable().cors().disable().authorizeRequests().antMatchers("/ank/getToken","/ank/user/save","/ank/notification")
					.permitAll().anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(authEntryPoint).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
			
			return httpSecurity.build();
		}
	}
}
