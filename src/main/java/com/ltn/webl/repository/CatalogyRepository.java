package com.ltn.webl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ltn.webl.entity.Catalogy;

@Repository
public interface CatalogyRepository extends CrudRepository<Catalogy, Long> {
	Catalogy findByName(String name);
}
