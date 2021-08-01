package com.springtutorials.dao;

import java.util.List;

import com.springtutorials.model.UserModel;

public interface IUserDAO {

	public List<UserModel> find();
	
	public List<String> findRoleByUsername(String username);
	
	public UserModel findOneByUsername(String username);
}
