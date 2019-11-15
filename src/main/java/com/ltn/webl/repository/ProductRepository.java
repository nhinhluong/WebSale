package com.ltn.webl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ltn.webl.entity.Product;

@Repository  
public interface ProductRepository extends CrudRepository<Product, Long> {
	//Product findProbyId(Long id);
}