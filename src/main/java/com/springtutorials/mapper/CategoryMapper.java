package com.springtutorials.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springtutorials.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		CategoryModel model = new CategoryModel();
		model.setId(rs.getString("ID"));
		model.setName(rs.getString("NAME"));
		model.setImage(rs.getString("IMAGE"));
		model.setParentId(rs.getString("PARENT_ID"));
		return model;
	}

}
