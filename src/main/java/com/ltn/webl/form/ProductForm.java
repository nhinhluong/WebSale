package com.ltn.webl.form;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.web.multipart.MultipartFile;
import com.ltn.webl.entity.Catalogy;
import com.ltn.webl.entity.Product;

public class ProductForm {
	private boolean newProduct = false;
	// Upload file.
    private MultipartFile fileData;
	private Long id;
	private String name;
	private String email;
	private String phone;
	private Set<Catalogy> productCate;
	private Long cat_id;
	
	public ProductForm() {
        this.newProduct= true;
    }
	
	public ProductForm(Product product) {
		//this.fileData = fileData;
		this.id = product.getId();
		this.name = product.getName();
		this.email = product.getEmail();
		this.phone = product.getPhone();
		this.cat_id = product.getCat_id();
		this.productCate = product.getCatalogies();
	}

	

	public Set<Catalogy> getProductCate() {
		return productCate;
	}

	public void setProductCate(Set<Catalogy> productCate) {
		this.productCate = productCate;
	}

	public MultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getCat_id() {
		return cat_id;
	}

	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}
	
	

}
