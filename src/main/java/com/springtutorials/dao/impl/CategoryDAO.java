package com.springtutorials.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springtutorials.dao.ICategoryDAO;
import com.springtutorials.mapper.CategoryMapper;
import com.springtutorials.model.CategoryModel;

@Repository
public class CategoryDAO extends JdbcDaoSupport implements ICategoryDAO{

	@Autowired
	public CategoryDAO(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.setDataSource(dataSource);
	}
	
	@Override
	public List<CategoryModel> find() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM CATEGORY";
		List<CategoryModel> list = this.getJdbcTemplate().queryForList(sql, CategoryModel.class);
		return list;
	}
	//1: Lay tat ca danh muc cha bang cach tim parentId = ""
	//2: Lay danh muc con theo danh muc cha

	@Override
	public List<CategoryModel> findByChildId(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM CATEGORY WHERE PARENT_ID <> ?";
		Object[] params = new Object[] {id};
		CategoryMapper mapper = new CategoryMapper();
		List<CategoryModel> list = this.getJdbcTemplate().query(sql, params, mapper);
		return list;
	}

	@Override
	public List<CategoryModel> findByParentId(String parentId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM CATEGORY WHERE PARENT_ID = ?";
		Object[] params = new Object[] {parentId};
		CategoryMapper mapper = new CategoryMapper();
		List<CategoryModel> list = this.getJdbcTemplate().query(sql, params, mapper);
		return list;
	}

	@Override
	public CategoryModel findOneById(String categoryId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM CATEGORY WHERE ID = ?";
		Object[] params = new Object[] {categoryId};
		CategoryMapper mapper = new CategoryMapper();
		CategoryModel model = this.getJdbcTemplate().queryForObject(sql,mapper, params);
		return model;
	}

}
