package com.ltn.webl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ltn.webl.entity.Product;

@Repository  
public interface ProductRepository extends CrudRepository<Product, Long> {

	@Query("select p from Product p where p.cat_id = :cat_id ")
	List<Product> findProductByCat(@Param("cat_id") Long cat_id);
}