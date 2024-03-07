package com.ms.user.query.service;

import java.util.List;

import com.ms.core.user.model.UserObj;

public interface IUserQueryService {

	public List<UserObj> getAllUsers();

	public List<UserObj> getAllActiveUsersOnly();

	public UserObj getUserByUserId(String userId);
	
	public boolean checkIfValidUser(String userId);
	
	boolean checkIfValidUserWithRole(String userId, String roleName);

}
