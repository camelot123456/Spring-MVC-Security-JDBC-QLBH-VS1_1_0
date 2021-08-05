package com.springtutorials.service;

import java.util.List;

import com.springtutorials.model.UserModel;

public interface IUserService {

	public List<UserModel> find();

	public List<String> findRoleByUsername(String username);

	public UserModel findOneByUsername(String username);

}
