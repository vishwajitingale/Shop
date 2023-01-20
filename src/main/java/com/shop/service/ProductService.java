package com.shop.service;

import java.util.List;

import com.shop.model.Product;
import com.shop.model.User;

public interface ProductService {
	
	public int createProduct(Product product);
	
	public List<Product> productDetails();

	public int delete(int id);
	
	public int change(Product product);
	
	public Product getProduct(int id);
	
	public boolean loginFun(String email,String password);
	
	public int saveUserData(User user);
	
	public boolean tokenVerification(String token);

}
