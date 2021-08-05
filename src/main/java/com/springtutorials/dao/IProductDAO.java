package com.springtutorials.dao;

import java.util.List;

import com.springtutorials.model.ProductModel;

public interface IProductDAO {

	List<ProductModel> find();

	ProductModel findById(String id);

	void save(ProductModel product);
	
	void updateOne(ProductModel product);

	void deleteOne(String id);

}
