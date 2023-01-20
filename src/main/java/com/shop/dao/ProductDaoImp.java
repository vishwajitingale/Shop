package com.shop.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.model.Product;
import com.shop.model.User;
import com.shop.row_mapper.RowMapperImp;
import com.shop.row_mapper.UserRowMapper;

@Repository
public class ProductDaoImp implements ProductDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public int insert (Product product) {
		try{String query="insert into product values(?,?,?)";
		int r=jdbcTemplate.update(query,product.getId(),product.getName(),product.getPrice());
		return r;}catch(Exception e) {
		return 2;
		}
	}
	
	@Transactional
	public List<Product> productList(){
		String query="select * from product";
		List<Product> productList=jdbcTemplate.query(query, new RowMapperImp());
		return productList;
	}
	
	@Transactional
	public int remove(int id){
		String query ="delete from product where id=?";
		int r =jdbcTemplate.update(query,id);
		return r;
	}
	
	@Transactional
	public int update(Product product) {
		String query="update product set name=?,price=? where id=?";
		int r=jdbcTemplate.update(query,product.getName(),product.getPrice(),product.getId());
		return r;
	}

	@Transactional
	public Product getProduct(int id) {
		try{String query= "select * from product where id=?";
		Product product=(Product)jdbcTemplate.queryForObject(query,new RowMapperImp(),id);
		return product;}
		catch (Exception e) {
		Product product=null;
		return product;
		}
	}
	
	@Transactional
	public User getUser(String email) {
		String query="select *from user where email=?";
		User user=(User)jdbcTemplate.queryForObject(query,new UserRowMapper(),email);
		return user;
	}
		
	@Transactional
	public int saveData(User user) {
		String query="insert into user(username,email,userpassword) values(?,?,?)";
		int r=jdbcTemplate.update(query,user.getName(),user.getEmail(),user.getPassword());
		return r;
	}
	
	@Transactional
	public User token(String token) {
		String query="select * from user where authenticationkey=?";
		User user=(User)jdbcTemplate.queryForObject(query,new UserRowMapper(),token);
		return user;
	}
	

}
