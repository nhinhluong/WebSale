package com.ltn.webl.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "pcode")
	private String pcode;

	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "price")
	private Double price;
	
	@Column(length = 65535, columnDefinition="TEXT", name = "description")
	private String description; 
	
	@Column(name = "isAvailable")
	private int isAvailable;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "catalogy_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "cat_id"))
	private Set<Catalogy> catalogies;

	private Long cat_id;

	@Lob
	@Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;
	
	
	public Product() {
	}
	
	public Product(String name, String pcode, int quantity, String type, Double price, String description,
			int isAvailable, Set<Catalogy> catalogies, Long cat_id, byte[] image) {
		super();
		this.name = name;
		this.pcode = pcode;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
		this.description = description;
		this.isAvailable = isAvailable;
		this.catalogies = catalogies;
		this.cat_id = cat_id;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCat_id() {
		return cat_id;
	}

	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Set<Catalogy> getCatalogies() {
		return catalogies;
	}

	public void setCatalogies(Set<Catalogy> catalogies) {
		this.catalogies = catalogies;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
