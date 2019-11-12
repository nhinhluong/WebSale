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
	 @GeneratedValue(strategy = GenerationType.AUTO)  
	  private Long id;  

	  @Column(name = "name")  
	  private String name;  

	  @Column(name = "level")  
	  private String level;

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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
	public Catalogy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Catalogy(Long id, String name, String level) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
	}
	

	  
}
