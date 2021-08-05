package com.springtutorials.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springtutorials.model.ProductModel;

public class ProductMapper implements RowMapper<ProductModel>{

	@Override
	public ProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		ProductModel product = new ProductModel();
		
		product.setId(rs.getString("ID"));
		product.setName(rs.getString("NAME"));
		product.setQuantity(rs.getInt("QUANTITY"));
		product.setPrice(rs.getDouble("PRICE"));
		product.setImage(rs.getString("IMAGE"));
		product.setCategoryId(rs.getString("CATEGORY_ID"));
		return product;
	}

}
