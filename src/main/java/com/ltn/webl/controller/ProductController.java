package com.ltn.webl.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ltn.webl.entity.Catalogy;
import com.ltn.webl.entity.Product;
import com.ltn.webl.entity.Role;
import com.ltn.webl.entity.User;
import com.ltn.webl.form.ProductForm;
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

	    return "home/homepage";  
	  }  

	  @RequestMapping(value = "addProduct")  
	  public String addProduct(Model model) {  
		 com.ltn.webl.form.ProductForm productForm = new com.ltn.webl.form.ProductForm(new Product());
		  
		List<Catalogy> listCata1 = catalogyService.getAllCatalogy();
		model.addAttribute("listCata1", listCata1);
	    model.addAttribute("product", new Product());  
	    model.addAttribute("productForm", productForm); 
	    return "home/product/addProduct";  
	  }  

	  @RequestMapping("product/edit/{id}")
	    public String edit(@PathVariable("id") Long id, Model model){
		  // lay tat ca thong tin ve		  
		   List<Catalogy> listCata1 = catalogyService.getAllCatalogy();
		   Optional<Product> productEdit = productService.findProductById(id);
		   Product product = productEdit.get();
		   ProductForm productForm = new ProductForm(product);
	        model.addAttribute("product", product);
	        model.addAttribute("listCata1", listCata1);
	        model.addAttribute("productForm", productForm); 
	        return "home/product/editProduct";
	    }
	  
	  @RequestMapping("productDetail/{id}")
	    public String showProduct(@PathVariable("id") Long id, Model model){
		   Optional<Product> productEdit = productService.findProductById(id);
		   Product product = productEdit.get();
		   List<Catalogy> catalogys = catalogyService.getAllCatalogy();  

		    model.addAttribute("catalogys", catalogys); 
	        model.addAttribute("product", product);
	        return "home/product/productDetail";
	    }
	  @PostMapping("/update/{id}")
	  public String updateProduct(@PathVariable("id") long id, @Valid Product product, 
	    BindingResult result, Model model, @RequestParam("fileData") MultipartFile fileData) {
	      if (result.hasErrors()) {
	          product.setId(id);
	          return "home/product/editProduct";
	      }
	      
	      // Get object Product cu
	      Optional<Product> existProductOp = productService.findProductById(product.getId());   
	     /* Kiem tra fileData co dc upload moi ko. Neu co thi thay doi, neu khong thi giu nguyen file cu.*/
	      byte[] imageExist = null;
	      try {
	    	  if(!fileData.isEmpty()) {    		  
	    		  imageExist = fileData.getBytes();
	    		  product.setImage(imageExist);
	    	  }else {    	    		  
	    		  imageExist = existProductOp.get().getImage();
	    		  product.setImage(imageExist);
	    	  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	           
	      productService.saveProduct(product);
	      model.addAttribute("products", productService.getAllProduct());
	      return "redirect:/home/home";
	  }
	  
	  @RequestMapping(value = "saveProduct", method = RequestMethod.POST)  
	  public String save(@Valid Product product, @RequestParam("fileData") MultipartFile fileData) {  
		try {
			byte[] image = fileData.getBytes();
			product.setImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	    productService.saveProduct(product);  
	    return "redirect:/home/home";  
	  }  

	  @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)  
	  public String deleteProduct(@RequestParam("id") Long productId, Model model) {  
	    productService.deleteProduct(productId);  
	    return "redirect:/home/home";  
	  }  
	  
	  @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
	    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
	            @RequestParam("id") Long id) throws IOException {
	        Optional<Product> productOp = null;
	        Product product = null;
	        if (id != null) {
	            productOp = productService.findProductById(id);
	            product = productOp.get();
	        }
	        if (product != null && product.getImage() != null) {
	            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	            response.getOutputStream().write(product.getImage());
	        }
	        response.getOutputStream().close();
	    }
}
