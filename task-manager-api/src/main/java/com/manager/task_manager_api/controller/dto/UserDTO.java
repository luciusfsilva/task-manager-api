package com.manager.task_manager_api.controller.dto;

import com.manager.task_manager_api.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String username;
	private String email;

	public UserDTO(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
	}

}
