package com.ank.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ank.exception.VehicleAlertException;
import static com.ank.literals.VehicleAlertLiterals.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	

	@Value("${token.secret}")
	private String secret;
	
	@Value("${token.validity}")
	private Long jwtTokenValidity;

	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Long getUserRefNumberFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return Long.valueOf((Integer)claims.get(USER_REFERENCE_ID));
	}

	public Date getExpirationDateFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails ,Long id) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(USER_REFERENCE_ID, id);
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = getUserNameFromToken(token);
		
		if(!username.equals(userDetails.getUsername())){
			throw new VehicleAlertException(INVALID_TOKEN_CODE, INVALID_TOKEN_MSG);
		}
		
		if(isTokenExpired(token)){
			throw new VehicleAlertException(TOKEN_EXPIRED_CODE, TOKEN_EXPIRED_MSG);
		}
		
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
		
	}
}
