package com.manager.task_manager_api.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.manager.task_manager_api.service.JWTService;

@Service
public class AuthenticationService {
	
	private final JWTService jwtService;
	
	public AuthenticationService(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
	public String authenticate(Authentication authentication) {
		return jwtService.generateToken(authentication);
	}

}
