package com.ltn.webl.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ltn.webl.entity.Catalogy;
import com.ltn.webl.entity.Product;
import com.ltn.webl.entity.Role;
import com.ltn.webl.entity.User;
import com.ltn.webl.service.CatalogyService;
import com.ltn.webl.service.ProductService;
import com.ltn.webl.service.RoleService;

@Controller
public class ProductController {
	@Autowired private ProductService productService;  
	
	@Autowired private CatalogyService catalogyService;

	  @RequestMapping("/home")  
	  public String index(Model model) {  
	    List<Product> products = productService.getAllProduct();  

	    model.addAttribute("products", products);  

	    return "home/home";  
	  }  

	  @RequestMapping(value = "addProduct")  
	  public String addProduct(Model model) {  
		List<Catalogy> listCata1 = catalogyService.getAllCatalogy();
		model.addAttribute("listCata1", listCata1);
	    model.addAttribute("product", new Product());  
	    return "home/product/addProduct";  
	  }  

	  @RequestMapping(value = "/editProduct", method = RequestMethod.GET)  
	  public String editProduct(@RequestParam("id") Long productId, Model model) {  
	    Optional<Product> productEdit = productService.findProductById(productId);  
	    productEdit.ifPresent(product -> model.addAttribute("product", product));  
	    return "home/product/editProduct";  
	  }  

	  @RequestMapping(value = "saveProduct", method = RequestMethod.POST)  
	  public String save(Product product) {  
	    productService.saveProduct(product);  
	    return "redirect:/home/home";  
	  }  

	  @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)  
	  public String deleteProduct(@RequestParam("id") Long productId, Model model) {  
	    productService.deleteProduct(productId);  
	    return "redirect:/home/home";  
	  }  
}
