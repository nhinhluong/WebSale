package com.ltn.webl.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ltn.webl.entity.Catalogy;
import com.ltn.webl.entity.Product;
import com.ltn.webl.entity.Role;
import com.ltn.webl.entity.User;
import com.ltn.webl.form.ProductForm;
import com.ltn.webl.model.FileModel;
import com.ltn.webl.repository.FileModelRepository;
import com.ltn.webl.service.CatalogyService;
import com.ltn.webl.service.ProductService;
import com.ltn.webl.service.RoleService;
import com.ltn.webl.service.UserService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private CatalogyService catalogyService;
	
	@Autowired
    FileModelRepository fileRepository;

	@RequestMapping(value={"/productList"}, method = RequestMethod.GET)
	public String index(Model model) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//User user = userService.findUserByEmail(auth.getName());
		List<Catalogy> catalogys = catalogyService.getAllCatalogy();  
		List<Product> products = productService.getAllProduct();
		model.addAttribute("catalogys", catalogys);
		model.addAttribute("products", products);
		//model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
		return "admin/product/productListView";
	}

	@RequestMapping("findByCatalogy/{id}")
	public String findProductByCatalogy(@PathVariable("id") Long cat_id, Model model) {
		// lay tat ca thong tin ve
		List<Catalogy> catalogys = catalogyService.getAllCatalogy();  
		List<Product> products= productService.findProductByCatalogy(cat_id);

		model.addAttribute("catalogys", catalogys);
		model.addAttribute("products", products); 
		return "/home/product/productListByCatalogy";
	}

	@RequestMapping(value = "addProduct")
	public String addProduct(Model model) {
		com.ltn.webl.form.ProductForm productForm = new com.ltn.webl.form.ProductForm(new Product());

		List<Catalogy> listCata1 = catalogyService.getAllCatalogy();
		model.addAttribute("listCata1", listCata1);
		model.addAttribute("product", new Product());
		model.addAttribute("productForm", productForm);
		return "admin/addProduct";
	}

	@RequestMapping("product/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		// lay tat ca thong tin ve
		List<Catalogy> listCata1 = catalogyService.getAllCatalogy();
		Optional<Product> productEdit = productService.findProductById(id);
		Product product = productEdit.get();
		ProductForm productForm = new ProductForm(product);
		model.addAttribute("product", product);
		model.addAttribute("listCata1", listCata1);
		model.addAttribute("productForm", productForm);
		return "admin/editProduct";
	}
	
	@RequestMapping("product/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {		
		productService.deleteProduct(id);
		
		List<Catalogy> catalogys = catalogyService.getAllCatalogy();  
		List<Product> products = productService.getAllProduct();
		model.addAttribute("catalogys", catalogys);
		model.addAttribute("products", products);
		
		return "admin/product/productListView";
	}

	@RequestMapping("productDetail/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		Optional<Product> productEdit = productService.findProductById(id);
		Product product = productEdit.get();
		List<Catalogy> catalogys = catalogyService.getAllCatalogy();

		model.addAttribute("catalogys", catalogys);
		model.addAttribute("product", product);
		return "user/product/detail";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable("id") long id, @Valid Product product, BindingResult result, Model model,
			@RequestParam("fileData") MultipartFile fileData) {
		if (result.hasErrors()) {
			product.setId(id);
			return "home/product/editProduct";
		}

		// Get object Product cu
		Optional<Product> existProductOp = productService.findProductById(product.getId());
		/*
		 * Kiem tra fileData co dc upload moi ko. Neu co thi thay doi, neu khong thi giu
		 * nguyen file cu.
		 */
		byte[] imageExist = null;
		try {
			if (!fileData.isEmpty()) {
				//imageExist = fileData.getBytes();
				//product.setImage(imageExist);
				/*note*: set max_allowed_packet = 256M in my.ini MYSQL if error Packet for query is too large */
				File convFile = convert(fileData);	
				//fileData.transferTo(convFile);
				BufferedImage inputImage = ImageIO.read(convFile);
				BufferedImage resized = resize(inputImage,320,640);
				File output = new File(fileData.getOriginalFilename());
		        ImageIO.write(resized, "png", output);
		        byte[] image1 = readBytesFromFile(output.getPath());
		        product.setImage(image1);
			} else {
				imageExist = existProductOp.get().getImage();
				product.setImage(imageExist);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		productService.saveProduct(product);
		model.addAttribute("products", productService.getAllProduct());
		return "redirect:/user/home";
	}

	@RequestMapping(value = "saveProduct", method = RequestMethod.POST)
	public String save(@Valid Product product, @RequestParam("fileData") MultipartFile[] fileDatas) {
		try {
			//byte[] image = fileData.getBytes();
			//product.setImage(image);
			//test multi file upload
			List<String> fileNames = new ArrayList<String>();
			List<FileModel> storedFile = new ArrayList<FileModel>();
			
			List<Product> list = new ArrayList<Product>();
			System.out.println("Multi: "+fileDatas.clone());
			for(MultipartFile fileData : fileDatas) {
				FileModel fileModel = fileRepository.findByName(fileData.getOriginalFilename());
				if(fileData!=null) {					
					
					System.out.println("Ten file: "+fileData.getOriginalFilename());
					System.out.println("Multi: "+fileData);
					/*note*: set max_allowed_packet = 256M in my.ini MYSQL if error Packet for query is too large */
					File convFile = convert(fileData);	
					//fileData.transferTo(convFile);
					BufferedImage inputImage = ImageIO.read(convFile);
					//resize image to 640x320
					BufferedImage resized = resize(inputImage,320,640);
					File output = new File(fileData.getOriginalFilename());
			        ImageIO.write(resized, "png", output);
			        byte[] image1 = readBytesFromFile(output.getPath());
			        product.setImage(image1);
			        if (fileModel != null) {
						//test uplaod multi file
				        fileModel.setPic(image1);
				        System.out.println("Product: "+product.getName());
						fileModel.setProduct(product);
						System.out.println("Product: "+product.getId());
						fileModel.setProduct_id(product.getId());
					}else {
						fileModel = new FileModel(fileData.getOriginalFilename(), fileData.getContentType(), fileData.getBytes(), product, product.getId());
					}
				}
				fileNames.add(fileData.getOriginalFilename());
				storedFile.add(fileModel);
			}
			
			fileRepository.saveAll(storedFile);			        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		productService.saveProduct(product);
		return "redirect:/productList";
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long productId, Model model) {
		productService.deleteProduct(productId);
		return "redirect:/admin/product/productListView";
	}

	@RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") Long id) throws IOException {

		Optional<Product> productOp = null;
		Product product = null;
		if (id != null) {
			productOp = productService.findProductById(id);
			product = productOp.get();
		}
		if (product != null && product.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getImage());
		}
		response.getOutputStream().close();
	}
	//resize image
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	//convert multipartfile to file goal to ImageIO read(file)
	public File convert(MultipartFile file)
	{    
	  File convFile = new File(file.getOriginalFilename());
	  try {
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile); 
		fos.write(file.getBytes());
		fos.close(); 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	  
	  return convFile;
	}
	//convert file to bytes[] to save in db
	private static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }
}
