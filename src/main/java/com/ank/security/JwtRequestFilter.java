package com.ank.security;

import static com.ank.literals.VehicleAlertLiterals.GENERIC_CODE;
import static com.ank.literals.VehicleAlertLiterals.INVALID_USER_CODE;
import static com.ank.literals.VehicleAlertLiterals.INVALID_USER_MSG;
import static com.ank.literals.VehicleAlertLiterals.TOKEN_EXPIRED_CODE;
import static com.ank.literals.VehicleAlertLiterals.TOKEN_EXPIRED_MSG;
import static com.ank.literals.VehicleAlertLiterals.TOKEN_SIGNATRE_ERROR_CODE;
import static com.ank.literals.VehicleAlertLiterals.TOKEN_SIGNATRE_ERROR_MSG;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.ank.domain.Login;
import com.ank.exception.VehicleAlertException;
import com.ank.repository.LoginRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;

	private final Log logger = LogFactory.getLog(JwtRequestFilter.class);

	@Autowired
	private JwtToken jwtToken;

	@Autowired
	private LoginRepository loginRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		try {

			final String requestTokenHeader = request.getHeader("Authorization");

			String username = null;
			String token = null;
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				token = requestTokenHeader.substring(7);
				try {
					username = jwtToken.getUserNameFromToken(token);
				} catch (IllegalArgumentException e) {
					logger.debug("Unable to get JWT Token");
					throw new VehicleAlertException(TOKEN_EXPIRED_CODE, TOKEN_EXPIRED_MSG);
				} catch (ExpiredJwtException e) {
					logger.debug("JWT Token has expired");
					throw new VehicleAlertException(TOKEN_EXPIRED_CODE, TOKEN_EXPIRED_MSG);

				}
			} else {
				logger.debug("JWT Token does not begin with Bearer String");
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = getUserDetails(token, username);

				if (jwtToken.validateToken(token, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			chain.doFilter(request, response);
		} catch (VehicleAlertException exception) {
			logger.debug("Error ocuured in jwt filter");
			resolver.resolveException(request, response, null,
					new VehicleAlertException(exception.getErrorCode(), exception.getErrorMessage()));
		}catch (SignatureException exception) {
			logger.debug("Error ocuured in jwt filter");
			resolver.resolveException(request, response, null,
					 new VehicleAlertException(TOKEN_SIGNATRE_ERROR_CODE, TOKEN_SIGNATRE_ERROR_MSG));
		} catch (Exception exception) {
			logger.debug("Error ocuured in jwt filter");
			resolver.resolveException(request, response, null,
					new VehicleAlertException(GENERIC_CODE, exception.getMessage()));
		}
	}

	private UserDetails getUserDetails(String token, String username) {

		Long refNumber = jwtToken.getUserRefNumberFromToken(token).longValue();
		Login loginDomain = loginRepository.getLoginUserDetailByByIdAndUser(refNumber, username);
		
		if (loginDomain != null) {
			
			List<GrantedAuthority> roles = loginDomain.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
			
			return new User(loginDomain.getUserName(), loginDomain.getPassword(), roles);
		}
		throw new VehicleAlertException(INVALID_USER_CODE, INVALID_USER_MSG);
	}

}