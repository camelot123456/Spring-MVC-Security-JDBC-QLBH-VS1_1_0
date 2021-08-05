package com.springtutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtutorials.dao.IProductDAO;
import com.springtutorials.model.ProductModel;
import com.springtutorials.service.IProductService;

@Service
public class ProductService implements IProductService{

	@Autowired
	private IProductDAO productDAO;
	
	@Override
	public List<ProductModel> find() {
		// TODO Auto-generated method stub
		return productDAO.find();
	}

	@Override
	public ProductModel findById(String id) {
		// TODO Auto-generated method stub
		return productDAO.findById(id);
	}

	@Override
	public void save(ProductModel product) {
		// TODO Auto-generated method stub
		productDAO.save(product);
	}

	@Override
	public void updateOne(ProductModel product) {
		// TODO Auto-generated method stub
		productDAO.updateOne(product);
	}

	@Override
	public void deleteOne(String id) {
		// TODO Auto-generated method stub
		productDAO.deleteOne(id);
	}

	@Override
	public void deleteMany(List<String> ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			productDAO.deleteOne(id);
		}
	}

}
