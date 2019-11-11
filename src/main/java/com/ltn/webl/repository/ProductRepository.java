package com.ltn.webl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltn.webl.entity.Product;


@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
 
 Product findByCode(String code);
 Product findByName(String name);

}