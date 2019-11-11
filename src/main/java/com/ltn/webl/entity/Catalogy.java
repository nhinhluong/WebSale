package com.ltn.webl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="catalogy")
public class Catalogy {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="cat_id")
	 private int id;
	 
	 @Column(name="catalogy")
	 private String catalogy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatalogy() {
		return catalogy;
	}

	public void setCatalogy(String catalogy) {
		this.catalogy = catalogy;
	}

}
