package com.springtutorials.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springtutorials.model.ProductModel;
import com.springtutorials.service.IProductService;

@RestController(value = "productAPIWeb")
public class ProductAPI {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductModel> productSelect() {
		List<ProductModel> products = productService.find();
		return products;
	}
	
	@PostMapping("/api/product")
	public ProductModel productInsert(@RequestBody ProductModel product) {
		return product;
	}
	
	@PutMapping("/api/product")
	public ProductModel productUpdate(@RequestBody ProductModel product) {
		return product;
	}
	
	@DeleteMapping("/api/product")
	public void productDelete(@RequestBody String[] ids) {
	}
	
}
