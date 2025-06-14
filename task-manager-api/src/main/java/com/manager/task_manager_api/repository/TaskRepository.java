package com.manager.task_manager_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.task_manager_api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {}
