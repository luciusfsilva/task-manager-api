package com.manager.task_manager_api.model;

import java.time.LocalDateTime;

import com.manager.task_manager_api.enumTask.UserRoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;
	
	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private LocalDateTime createdAt;

	void onCreated() {
		this.createdAt = LocalDateTime.now();
		this.enabled = true;
		if (this.role == null) this.role = UserRoleEnum.USER; // Default role
		
	}

}
