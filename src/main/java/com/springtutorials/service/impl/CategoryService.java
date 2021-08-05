package com.springtutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtutorials.dao.ICategoryDAO;
import com.springtutorials.model.CategoryModel;
import com.springtutorials.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> find() {
		// TODO Auto-generated method stub
		return categoryDAO.find();
	}

	@Override
	public List<CategoryModel> findByChildId(String id) {
		// TODO Auto-generated method stub
		return categoryDAO.findByChildId(id);
	}

	@Override
	public List<CategoryModel> findByParentId(String parentId) {
		// TODO Auto-generated method stub
		return categoryDAO.findByParentId(parentId);
	}

	@Override
	public CategoryModel findOneById(String categoryId) {
		// TODO Auto-generated method stub
		return categoryDAO.findOneById(categoryId);
	}

}
