package com.ltn.webl.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 
	 @Column(name = "code")
	 private String code;
	 
	 @Column(name = "name")
	 private String name; 
	 
	 @Column(name = "amount")
	 private Double amount;
	 
	 @Column(name = "quantity")
	 private Double quantity;
	 
	 @Column(name = "active")
	 private int active;
	 
	 @ManyToMany(cascade=CascadeType.ALL)
	 @JoinTable(name="catalogy_product", joinColumns=@JoinColumn(name="product_id"), inverseJoinColumns=@JoinColumn(name="cat_id"))
	 private Set<Catalogy> catalogies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code =code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Catalogy> getCatalogies() {
		return catalogies;
	}

	public void setCatalogies(Set<Catalogy> catalogies) {
		this.catalogies = catalogies;
	}

	 
}
