package com.manager.task_manager_api.dto;

import java.time.LocalDateTime;

import com.manager.task_manager_api.enumTask.TaskStatusEnum;
import com.manager.task_manager_api.model.Task;

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
public class TaskDTO {
	
	private String title;
	private String description;
	private TaskStatusEnum status;
	private LocalDateTime createdAt;
	private UserDTO assignedTo;

	public TaskDTO(Task task) {
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.status = task.getStatus();
		this.createdAt = task.getCreatedAt();
		this.assignedTo = new UserDTO(task.getUser());
	}

}
