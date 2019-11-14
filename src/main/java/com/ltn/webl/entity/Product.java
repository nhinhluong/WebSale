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

@Entity
@Table(name = "product")
public class Product {
	@Id  
	 @GeneratedValue(strategy = GenerationType.AUTO)  
	  private Long id;  

	  @Column(name = "name")  
	  private String name;  

	  @Column(name = "email")  
	  private String email;  

	  @Column(name = "phone")  
	  private String phone;  
	  
	  @ManyToMany(cascade = CascadeType.ALL)
	  @JoinTable(name = "catalogy_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "cat_id"))
	  private Set<Catalogy> catalogies;
	  
	  private Long cat_id; 

	  public Product() {}  

	  public Product(String name, String email, String phone) {  
	    this.name = name;  
	    this.email = email;  
	    this.phone = phone;  
	  }  
	  

	  public Product(String name, String email, String phone, Set<Catalogy> catalogies) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.catalogies = catalogies;
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



	public Set<Catalogy> getCatalogies() {
		return catalogies;
	}

	public void setCatalogies(Set<Catalogy> catalogies) {
		this.catalogies = catalogies;
	}

}
