package com.manager.task_manager_api.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

	private final JwtEncoder jwtEncoder;
	
	public JWTService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		
		long expiresIn = 3600; // 1 hour in seconds
		
		// Collect the authorities (scopes) from the authentication object
		String escopes = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		
		var claims = JwtClaimsSet.builder()
				.issuer("task-manager-api")
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiresIn))
				.subject(authentication.getName())
				.claim("scopes", escopes)
				.build();
				
		// Encode the JWT token with the claims
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}
