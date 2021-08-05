package com.springtutorials.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springtutorials.dao.IProductDAO;
import com.springtutorials.mapper.ProductMapper;
import com.springtutorials.model.ProductModel;

@Repository
public class ProductDAO extends JdbcDaoSupport implements IProductDAO{

	@Autowired
	public ProductDAO(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.setDataSource(dataSource);
	}
	
	@Override
	public List<ProductModel> find() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM PRODUCT";
		ProductMapper mapper = new ProductMapper();
		List<ProductModel> list = this.getJdbcTemplate().query(sql, mapper);
		return list;
	}

	@Override
	public ProductModel findById(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
		Object[] params = new Object[] {id};
		ProductMapper mapper = new ProductMapper();
		ProductModel model = this.getJdbcTemplate().queryForObject(sql, mapper, params);
		return model;
	}

	@Override
	public void save(ProductModel product) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO PRODUCT(ID, NAME, QUANTITY, PRICE, IMAGE, CATEGORY_ID) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[] 
				{
						product.getId(),
						product.getName(),
						product.getQuantity(),
						product.getPrice(),
						product.getImage(),
						"DM0006"
				};
		this.getJdbcTemplate().update(sql, params);
	}

	@Override
	public void updateOne(ProductModel product) {
		// TODO Auto-generated method stub
		String sql = "UPDATE PRODUCT SET NAME=?, QUANTITY=?, PRICE=?, IMAGE=?, DESCRIPTION=?, CATEGORY_ID=? WHERE ID = ?";
		Object[] params = new Object[] {product.getName(), product.getQuantity(), product.getPrice(), product.getImage(), product.getDescription(), product.getCategoryId(), product.getId()};
		this.getJdbcTemplate().update(sql, params);
	}

	@Override
	public void deleteOne(String id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM PRODUCT WHERE ID=?";
		Object[] params = new Object[] {id};
		this.getJdbcTemplate().update(sql, params);
	}

}
