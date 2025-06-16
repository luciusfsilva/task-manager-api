package com.manager.task_manager_api.service;

import com.manager.task_manager_api.model.User;

public interface UserDetail {
	
	public String getByPasswordCrypt(String password);
	
	public String getByPasswordDescrypt(String passwordCrypt);
	
	public User getByUserName(String userName);

}
