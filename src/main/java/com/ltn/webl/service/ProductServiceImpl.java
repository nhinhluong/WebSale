package com.ltn.webl.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ltn.webl.entity.Catalogy;
import com.ltn.webl.entity.Product;
import com.ltn.webl.entity.Role;
import com.ltn.webl.repository.CatalogyRepository;
import com.ltn.webl.repository.ProductRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired private ProductRepository productRepository;  

	  @Override  
	  public List<Product> getAllProduct() {  
	    return (List<Product>) productRepository.findAll();  
	  }  

	  @Override  
	  public void saveProduct(Product product) {  
		  productRepository.save(product);  
	  }  

	  @Override  
	  public void deleteProduct(Long id) {  
		  productRepository.deleteById(id);  
	  }  

	  @Override  
	  public Optional<Product> findProductById(Long id) {  
	    return productRepository.findById(id);  
	  }  
}
