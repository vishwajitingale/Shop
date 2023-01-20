package com.shop.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.ProductService;


@Controller
public class HomeController {
	
	@Autowired
	private Product product;
	
	@Autowired
	private User user;
	
	@Autowired
	private ProductService service;
	
	//Interface
	@RequestMapping("home")
	public String home() {
		return "index";
	}
	
	//Insert Product
	@RequestMapping(path="saveProduct" ,method=RequestMethod.POST)
	public String save(HttpServletRequest request) {
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int price=Integer.parseInt(request.getParameter("price"));
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		service.createProduct(product);
		return "redirect:list";
	}
	
	//View Product List
	@RequestMapping(path="list",method=RequestMethod.GET)
	public String productList(Model model) {
		model.addAttribute("productList",service.productDetails());	
		return "ProductList";
	}
	
	//Delete Product
	@RequestMapping(path="delete",method=RequestMethod.GET)
	public String Remove(HttpServletRequest request) {
		int id=Integer.parseInt(request.getParameter("id"));
		service.delete(id);
		return "redirect:list";
	}
	
	//Update Product
	@RequestMapping(path="edit",method=RequestMethod.GET)
	public String update(HttpServletRequest request,Model model) {
		int id=Integer.parseInt(request.getParameter("id"));
		model.addAttribute("productObj",service.getProduct(id));
		return "edit";
	}
	
	//Update Product 
	@RequestMapping(path="update")
	public String update(HttpServletRequest request) {
		product.setId(Integer.parseInt(request.getParameter("productId")));
		product.setName(request.getParameter("productName"));
		product.setPrice(Integer.parseInt(request.getParameter("productPrice")));
		service.change(product);
		return "redirect:list";
	}
	
	@RequestMapping("process")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		ModelAndView model=new ModelAndView();
		String emailId=request.getParameter("email");
		boolean r=service.loginFun(emailId,request.getParameter("password"));
		if(r) {
			session =request.getSession();
			session.setAttribute("userId",emailId);
			model.setViewName("redirect:list");
		}else {
		model.addObject("msg","Invalied Credentials. Please try again...");
		model.setViewName("loginPage");}
		return model;
	}
	
	@RequestMapping("/")
	public String loginPage(HttpServletRequest request) {
		HttpSession s=request.getSession();
		s.removeAttribute("userId");
		return"loginPage";
	}
	
	@RequestMapping("login")
	public String login() {
		return"loginPage";
	}
	
	@RequestMapping("register")
	public String register() {
		return"register";
	}
	
	@RequestMapping(path="save", method=RequestMethod.POST)
	public ModelAndView saveUserData(HttpServletRequest request) {
		ModelAndView model=new ModelAndView();

		model.addObject("uName",request.getParameter("fname"));
		model.addObject("mail",request.getParameter("email"));

		String confirm =request.getParameter("rpass");
		String password=request.getParameter("pass");
			if(confirm.equals(password)) {
		user.setName(request.getParameter("fname"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(password);
		service.saveUserData(user);
		model.setViewName("loginPage");
		return model;
		}
			model.addObject("msg","Password Not Match. Please Confirm Password");
			model.setViewName("register");
		return model;
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession s=request.getSession();
		s.removeAttribute("userId");
		return "redirect:login";
	}
	
	
	@RequestMapping("saveData")
	public ModelAndView downloadData() {
		ModelAndView model= new ModelAndView();
		List<Product> list=service.productDetails();
		try {
			PrintWriter pw = new PrintWriter(new File("C:\\Users\\Lenovo\\Downloads\\ProductData.csv"));
			StringBuilder sb = new StringBuilder();
			
			sb.append("Id");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Price");
			sb.append("\r\n");
			
			for(Product s:list) {
				sb.append(s.getId());
				sb.append(",");
				sb.append(s.getName());
				sb.append(",");
				sb.append(s.getPrice());
				sb.append("\r\n");
			}
			
			pw.write(sb.toString());
			pw.close();
			
			model.addObject("msg","Product List Downloaded");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:list");
	}

}
