package com.ltn.webl.service;

import java.util.List;
import java.util.Optional;

import com.ltn.webl.entity.Product;

public interface ProductService {
	List<Product> getAllProduct();  

	  void saveProduct(Product product);  

	  void deleteProduct(Long id);  

	  Optional<Product> findProductById(Long id); 

}
