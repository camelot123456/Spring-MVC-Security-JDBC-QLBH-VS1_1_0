package com.springtutorials.dao;

import java.util.List;

import com.springtutorials.model.CategoryModel;

public interface ICategoryDAO {

	List<CategoryModel> find();
	
	List<CategoryModel> findByChildId(String id);
	
	List<CategoryModel> findByParentId(String parentId);

	CategoryModel findOneById(String categoryId);
	
}
