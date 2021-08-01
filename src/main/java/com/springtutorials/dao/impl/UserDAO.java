package com.springtutorials.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springtutorials.dao.IUserDAO;
import com.springtutorials.mapper.UserMapper;
import com.springtutorials.model.UserModel;

@Repository
public class UserDAO extends JdbcDaoSupport implements IUserDAO{

	@Autowired
	public UserDAO(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.setDataSource(dataSource);
	}
	
	@Override
	public List<UserModel> find() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM [USER]";
		UserMapper mapper = new UserMapper();
		List<UserModel> list = this.getJdbcTemplate().query(sql , mapper);
		return list;
	}

	@Override
	public UserModel findOneByUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM [USER] WHERE USERNAME = ?";
		Object[] params = new Object[] {username};
		UserMapper mapper = new UserMapper();
		UserModel userModel = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return userModel;
	}

	@Override
	public List<String> findRoleByUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT CODE FROM [ROLE] WHERE USERNAME = ?";
		Object[] params = new Object[] {username};
		
		List<String> list = this.getJdbcTemplate().queryForList(sql, params, String.class);
		return list;
	}


}
