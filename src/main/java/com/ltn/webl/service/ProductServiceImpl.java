package com.ltn.webl.service;

import java.util.Arrays;
import java.util.HashSet;

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

	@Autowired
	ProductRepository productRepository;
	
	//@Autowired
	Catalogy catalogy;

	@Autowired
	CatalogyRepository catalogyRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Product findProductbyName(String p_name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(p_name);
	}

	@Override
	public Product findProductbyCode(String p_code) {
		// TODO Auto-generated method stub
		return productRepository.findByCode(p_code);
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		//product.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		product.setActive(1);
		Catalogy productCat = catalogyRepository.findByCatalogy(catalogy.getCatalogy());
		product.setCatalogies(new HashSet<Catalogy>(Arrays.asList(productCat)));
		productRepository.save(product);
	}

}
