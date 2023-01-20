package com.shop.service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.shop.dao.ProductDao;
import com.shop.model.Product;
import com.shop.model.User;

@Service
public class ProductServiceImp implements ProductService{
	
    @Autowired
	private ProductDao productDao;
    
	
	public int createProduct(Product product) {
		return this.productDao.insert(product);
	}
	
	public List<Product> productDetails(){
		return productDao.productList();
	}

	public int delete(int id) {
		return productDao.remove(id);
	}
	
	public int change(Product product) {
		return productDao.update(product);
	}

	@Override
	public Product getProduct(int id) {
		return productDao.getProduct(id);
	}

	@Override
	public boolean loginFun(String email,String password) {
		
		try{User user = productDao.getUser(email);
		String encodePassword=user.getPassword();
		Decoder decoder=Base64.getDecoder();
		byte[] pass=decoder.decode(encodePassword);
		//System.out.println("In login Fun"+new String(pass));
		user.setPassword(new String(pass));
		
		if(email.equals(user.getEmail()) && password.equals(user.getPassword())) {
			return true;
		}
		}catch(Exception e) {
		return false;	
		}
		return false;
	}
	
	public int saveUserData(User user) {
		String pass=user.getPassword();
		Encoder encoder=Base64.getEncoder();
		String encodePassword=encoder.encodeToString(pass.getBytes());
		user.setPassword(encodePassword);
		//System.out.println(encodePassword);
		return productDao.saveData(user); 
	}

	@Override
	public boolean tokenVerification(String token) {
		System.out.println("inside Service ");
			try{User user=productDao.token(token);
			if(user != null) {
			return true;
			}else {
			return false;}
			}catch(Exception e) {
			}
		return false;	
	}	
}

