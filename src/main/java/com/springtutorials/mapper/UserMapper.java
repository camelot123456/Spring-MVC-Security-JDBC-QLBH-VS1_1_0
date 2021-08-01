package com.springtutorials.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springtutorials.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserModel user = new UserModel();
		user.setId(rs.getString("ID"));
		user.setFullname(rs.getString("FULLNAME"));
		user.setUsername(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setAvatar(rs.getString("AVATAR"));
		return user;
	}

}
