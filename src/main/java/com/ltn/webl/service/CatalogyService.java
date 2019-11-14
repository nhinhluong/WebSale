package com.ltn.webl.service;

import java.util.List;
import java.util.Optional;

import com.ltn.webl.entity.Catalogy;

public interface CatalogyService {
	List<Catalogy> getAllCatalogy();  

	  void saveCatalogy(Catalogy catalogy);  

	  void deleteCatalogy(Long id);  
	  
	  Optional<Catalogy> findCatalogyById(Long id); 

	  public Catalogy findCatById(Long id); 
}