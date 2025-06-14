package com.manager.task_manager_api.model;

import java.time.LocalDateTime;

import com.manager.task_manager_api.enumTask.TaskStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
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
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "title", nullable = false, length = 5000)
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatusEnum status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updatedAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	void taskDateTimeStatus() {
		this.status = TaskStatusEnum.PENDING;
		this.createdAt = LocalDateTime.now();
	}
	
	void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}

}
