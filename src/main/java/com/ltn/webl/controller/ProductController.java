package com.ltn.webl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ltn.webl.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping(value= {"/productList"}, method=RequestMethod.GET)
	 public ModelAndView productList() {
	  ModelAndView model = new ModelAndView();
	  
	  model.setViewName("home/product/productList");
	  return model;
	 }

}
