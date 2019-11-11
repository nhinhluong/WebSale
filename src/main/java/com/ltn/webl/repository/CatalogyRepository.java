package com.ltn.webl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltn.webl.entity.Catalogy;


@Repository("catalogyRepository")
public interface CatalogyRepository extends JpaRepository<Catalogy, Integer> {

	Catalogy findByCatalogy(String catalogy);
}