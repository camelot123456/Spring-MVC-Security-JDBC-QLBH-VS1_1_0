package com.springtutorials.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtutorials.constant.SystemContant;
import com.springtutorials.dao.IProductDAO;
import com.springtutorials.model.ProductModel;
import com.springtutorials.service.IProductService;
import com.springtutorials.utils.PagedListUtil;

@Service
public class ProductService implements IProductService {

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

	@Override
	public int getMyPageSize(int pageSize, int fetch) {
		// TODO Auto-generated method stub
		return (pageSize + fetch - 1) / fetch;
	}

	@Override
	public void handlerPagedList(String type, HttpServletRequest req) {
		// TODO Auto-generated method stub
		int count = getMyPageSize(find().size(), SystemContant.FETCH);
		new PagedListUtil<ProductModel>().handlerPagedList(type, req, find(), count, SystemContant.PRODUCTS);
	}
	
	@Override
	public void handlerPagedListTrash(String type, HttpServletRequest req) {
		// TODO Auto-generated method stub
		int count = getMyPageSize(findByDeleted(Boolean.FALSE).size(), SystemContant.FETCH);
		new PagedListUtil<ProductModel>().handlerPagedList(type, req, findByDeleted(Boolean.FALSE), count, SystemContant.PRODUCTS);
	}

	@Override
	public void updateOneDeletedByID(String id, Boolean deleted) {
		// TODO Auto-generated method stub
		productDAO.updateOneDeletedByID(id, deleted);
	}
	
	

	@Override
	public List<ProductModel> findByDeleted(Boolean deleted) {
		// TODO Auto-generated method stub
		return productDAO.findByDeleted(deleted);
	}

	@Override
	public void updateManyDeletedByID(List<String> ids, Boolean deleted) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			productDAO.updateOneDeletedByID(id, deleted);
		}
	}

}
