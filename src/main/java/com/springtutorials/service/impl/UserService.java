package com.springtutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtutorials.dao.IUserDAO;
import com.springtutorials.model.UserModel;
import com.springtutorials.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public List<UserModel> find() {
		// TODO Auto-generated method stub
		return userDAO.find();
	}

	@Override
	public List<String> findRoleByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.findRoleByUsername(username);
	}

	@Override
	public UserModel findOneByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.findOneByUsername(username);
	}

}
