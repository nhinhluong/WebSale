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
    //multi file
    private MultipartFile[] multiData;
	private Long id;
	private String name;
	private String pcode;
	private int quantity;
	private String type;
	private Double price;
	private String description;
	private String shortdesc;
	private int isAvailable;
	private Set<Catalogy> productCate;
	private Long cat_id;
	
	private byte [] productImage;
	
	public ProductForm() {
        this.newProduct= true;
    }
	
	public ProductForm(Product product) {
		//this.fileData = fileData;
		this.id = product.getId();
		this.name = product.getName();
		this.pcode = product.getPcode();
		this.type = product.getType();
		this.quantity = product.getQuantity();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.shortdesc = product.getShortdesc();
		this.isAvailable = product.getIsAvailable();
		this.cat_id = product.getCat_id();
		this.productCate = product.getCatalogies();
		this.productImage = product.getImage();
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

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortdesc() {
		return shortdesc;
	}

	public void setShortdesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Long getCat_id() {
		return cat_id;
	}

	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public MultipartFile[] getMultiData() {
		return multiData;
	}

	public void setMultiData(MultipartFile[] multiData) {
		this.multiData = multiData;
	}	

}
