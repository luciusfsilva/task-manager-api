package com.manager.task_manager_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.task_manager_api.model.User;

public interface UsersRepository extends JpaRepository<User, Long> {

	User existsById(String login);}
