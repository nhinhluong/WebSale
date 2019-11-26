package com.ltn.webl.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ltn.webl.entity.Catalogy;
import com.ltn.webl.entity.Product;
import com.ltn.webl.form.ProductForm;
import com.ltn.webl.repository.CatalogyRepository;
import com.ltn.webl.repository.ProductRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	// @Autowired
	private SessionFactory sessionFactory;
	// Upload file.
	private MultipartFile fileData;

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CatalogyRepository catalogyRepository;

	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void saveProduct(Product product) {
		Catalogy cate = new Catalogy();
		cate = catalogyRepository.findCatById(product.getCat_id());
		product.setCatalogies(new HashSet<Catalogy>(Arrays.asList(cate)));
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Optional<Product> findProductById(Long id) {
		return productRepository.findById(id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void save(ProductForm productForm) {
		Session session = this.sessionFactory.getCurrentSession();
		// Long id = productForm.getId();

		Product product = null;
		Catalogy cate = new Catalogy();
		cate = catalogyRepository.findCatById(product.getCat_id());
		product.setCatalogies(new HashSet<Catalogy>(Arrays.asList(cate)));
		product.setId(productForm.getId());
		product.setName(productForm.getName());
		product.setPcode(productForm.getPcode());
		product.setType(productForm.getType());
		product.setQuantity(productForm.getQuantity());
		product.setPrice(productForm.getPrice());
		product.setDescription(productForm.getDescription());
		product.setShortdesc(productForm.getShortdesc());
		product.setIsAvailable(1);
		if (productForm.getFileData() != null) {
			byte[] image = null;
			try {
				image = productForm.getFileData().getBytes();
			} catch (IOException e) {
			}
			if (image != null && image.length > 0) {
				product.setImage(image);
			}
		}

		// Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
		session.flush();
	}

	// @Override
	// public Product findProbyId(Long id) {
	// // TODO Auto-generated method stub
	// return productRepository.findProbyId(id);
	// }

}
