package com.ltn.webl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltn.webl.entity.Catalogy;
import com.ltn.webl.entity.Product;
import com.ltn.webl.repository.CatalogyRepository;
import com.ltn.webl.repository.ProductRepository;

@Service("catalogyService")
public class CatalogyServiceImpl implements CatalogyService {

	@Autowired
	CatalogyRepository catalogyRepository;

	@Override
	public List<Catalogy> getAllCatalogy() {
		return (List<Catalogy>) catalogyRepository.findAll(); 
	}

	@Override
	public void deleteCatalogy(Long id) {
		// TODO Auto-generated method stub
		catalogyRepository.deleteById(id);
	}

	@Override
	public void saveCatalogy(Catalogy catalogy) {
		// TODO Auto-generated method stub
		catalogyRepository.save(catalogy);
	}

	@Override
	public Catalogy findCatById(Long id) {
		// TODO Auto-generated method stub
		return catalogyRepository.findCatById(id);
	}

	@Override
	public Optional<Catalogy> findCatalogyById(Long id) {
		// TODO Auto-generated method stub
		return catalogyRepository.findById(id);
	}

}
