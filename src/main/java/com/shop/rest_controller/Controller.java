package com.shop.rest_controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.ProductService;

@RestController
public class Controller {
	
	@Autowired
	private ProductService productService;
	
	
    //View Product List
	@GetMapping("plist")
	public List<Product> list() {
		List<Product> productList=productService.productDetails();
		return  productList;
		
	}
	
	//Insert Product
	@PostMapping("plist")
	public ResponseEntity<Object> insert(@RequestBody Product product,@RequestHeader HttpHeaders header) {
		Map<String,Object> response=new HashMap<String,Object>();
		List<String> token=header.get("token");
		String t = token.get(0);
		boolean result=productService.tokenVerification(t);
		if(result) {
		if(product==null) {
			response.put("message", "Please Insert Data");
			response.put("Status", "Failed");
			return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST) ;
		}
		int r =productService.createProduct(product);
	    if(r==1) {
	    	response.put("message","Data Inserted Successfully" );
	    	response.put("status", "Success");
	    	return new ResponseEntity<Object>(response,HttpStatus.OK) ;
	    }else if(r==2) {
	    	response.put("message", "Product Id "+product.getId()+" is already exist. Please try with other Id");
	    	response.put("status","Failed");
	    	return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	    	}
		}
		response.put("message","Invalid Token");
		response.put("status","Failed");
		return new ResponseEntity<Object>(response,HttpStatus.EXPECTATION_FAILED) ;
	}
	
	//Update Product Data
	@PutMapping("plist")
	public ResponseEntity<Object> update(@RequestBody Product product,@RequestHeader HttpHeaders header) {
		List<String> token= header.get("token");
		Map<String,Object> response=new HashMap<String,Object>();
		
		String t=token.get(0);
		boolean result=productService.tokenVerification(t);
		if(result) {
		int r =productService.change(product);
		if(r==1) {
			response.put("status", "Success");
			response.put("message", "Data Updated Successfully");
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}else {
		response.put("message","Please Enter Valid Product Id");
		response.put("status","Failed");
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
		}
		}
		response.put("message","Invalid Token");
		response.put("status","Failed");
		return new ResponseEntity<Object>(response,HttpStatus.EXPECTATION_FAILED);
	}
	
	//Delete Product
	@DeleteMapping("plist/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id,@RequestHeader HttpHeaders header) {
		Map<String,Object> response=new HashMap<String,Object>();
		List<String> token=header.get("token");
		String t=token.get(0);
		boolean result=productService.tokenVerification(t);
		System.out.println("Reult is = "+result);
		if(result){
		int r=productService.delete(id);
		if(r==1) {
			response.put("status","success");
			response.put("message", "Data Deleted Successfully");
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		}
		response.put("status","Failed");
		response.put("message","Invalied Token");
		return new ResponseEntity<Object>(response,HttpStatus.EXPECTATION_FAILED);	
	}
	
	//view single product
	@GetMapping("plist/{id}")
	public String getProduct(@PathVariable int id) {
		Product product= productService.getProduct(id);
		if(product==null) {
			return "Please Enter Valid Product Id";
		}
		return ""+product;
	}
	
	@PostMapping("login") 
	public String login(@RequestBody User user) {
		
		boolean r=productService.loginFun(user.getEmail(),user.getPassword());
		if(r) {
			return "Login Successfully";
		}else {
		return "Invalied Credentials. Please try again...";	 
		}
	}
	
	@PostMapping("register")
	public String register(@RequestBody User user) {
		if(user != null && user.getPassword() != null) {
		productService.saveUserData(user);
			return "Register Successfully";
		}
		return "Invalied Credentials. Please try again...";
	}
	

}
