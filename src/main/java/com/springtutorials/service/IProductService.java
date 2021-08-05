package com.springtutorials.service;

import java.util.List;

import com.springtutorials.model.ProductModel;

public interface IProductService {

	List<ProductModel> find();

	ProductModel findById(String id);

	void save(ProductModel product);
	
	void updateOne(ProductModel product);

	void deleteOne(String id);

	void deleteMany(List<String> ids);
	
}
