package com.springtutorials.service;

import java.util.List;

import com.springtutorials.model.CategoryModel;

public interface ICategoryService {

	List<CategoryModel> find();

	List<CategoryModel> findByChildId(String id);

	List<CategoryModel> findByParentId(String parentId);

	CategoryModel findOneById(String categoryId);

}
