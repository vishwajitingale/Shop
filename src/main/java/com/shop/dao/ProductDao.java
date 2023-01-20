package com.shop.dao;

import java.util.List;
import com.shop.model.Product;
import com.shop.model.User;

public interface ProductDao {
	
	public int insert (Product product);
	
	public List<Product> productList();
	
	public int remove(int id);
	
	public int update(Product product);
	
	public Product getProduct(int id);
	
	public User getUser(String email);
	
	public int saveData(User user);
	
	public User token(String token);

}
