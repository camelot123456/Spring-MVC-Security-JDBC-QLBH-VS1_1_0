package com.springtutorials.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.springtutorials.model.ProductModel;

public interface IProductService {
	
	List<ProductModel> find();

	ProductModel findById(String id);

	void save(ProductModel product);
	
	void updateOne(ProductModel product);

	void deleteOne(String id);

	void deleteMany(List<String> ids);
	
	public int getMyPageSize(int pageSize, int fetch);
	
	public void handlerPagedList(String type, HttpServletRequest req);
	
	public void handlerPagedListTrash(String type, HttpServletRequest req);
	
	void updateOneDeletedByID(String id, Boolean deleted);
	
	void updateManyDeletedByID(List<String> ids, Boolean deleted);

	List<ProductModel> findByDeleted(Boolean deleted);
	
}
