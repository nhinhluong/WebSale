package com.ltn.webl.model;

import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ltn.webl.entity.Product;

@Entity
@Table(name = "file_resource")
@Data
public class FileModel extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mimetype")
    private String mimetype;

    @Lob
    @Column(name = "pic")
    private byte[] pic;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "product_image", joinColumns = @JoinColumn(name = "file_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Product product;
    
    private Long product_id;
    
    public FileModel() {
    }

	public FileModel(String name, String mimetype, byte[] pic, Product product, Long product_id) {
		super();
		this.name = name;
		this.mimetype = mimetype;
		this.pic = pic;
		this.product = product;
		this.product_id = product_id;
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

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
    
    
}