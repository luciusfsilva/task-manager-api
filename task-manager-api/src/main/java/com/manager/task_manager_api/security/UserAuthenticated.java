package com.manager.task_manager_api.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.manager.task_manager_api.model.User;

public class UserAuthenticated implements UserDetails {

	private static final long serialVersionUID = -4950522745326928742L;
	
	private final User user;
	
	public UserAuthenticated(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
	}

	@Override public String getPassword() {return user.getPassword();}

	@Override public String getUsername() {return user.getUsername();}
	
	@Override public boolean isAccountNonExpired() {return true;}
	
	@Override public boolean isCredentialsNonExpired() {return true;}
	
	@Override public boolean isEnabled() {return true;}
	
	@Override public boolean isAccountNonLocked() {return true;}

}
