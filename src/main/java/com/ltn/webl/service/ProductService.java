package com.ltn.webl.service;

import java.util.List;
import java.util.Optional;

import com.ltn.webl.entity.Product;
import com.ltn.webl.form.ProductForm;

public interface ProductService {
	List<Product> getAllProduct();  

	  void saveProduct(Product product);  
	  
	 // void saveProductForm(ProductForm productForm); 

	  void deleteProduct(Long id);  

	  Optional<Product> findProductById(Long id); 
	  
	  //Product findProbyId(Long id);

}
