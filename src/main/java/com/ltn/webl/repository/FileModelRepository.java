package com.ltn.webl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltn.webl.model.FileModel;

@Repository
public interface FileModelRepository extends JpaRepository<FileModel, Long> {

	FileModel findByName(String name);
	//List<FileModel> findbyProduct(Long product_id);
}
