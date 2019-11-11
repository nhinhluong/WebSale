package com.ltn.webl.service;

import com.ltn.webl.entity.Product;

public interface ProductService {
	public Product findProductbyName(String p_name);
	public Product findProductbyCode(String p_code);
	public void saveProduct(Product product);

}
