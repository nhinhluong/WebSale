package com.ltn.webl.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ltn.webl.entity.Product;
import com.ltn.webl.entity.Role;
import com.ltn.webl.entity.User;
import com.ltn.webl.service.ProductService;
import com.ltn.webl.service.RoleService;
import com.ltn.webl.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired private ProductService productService;  
	
	@Autowired private RoleService roleService;

	@RequestMapping(value = {"/","/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();

		model.setViewName("user/login");
		return model;
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		List<Role> listRole1 = roleService.getAllRole();
		model.addObject("user", user);
		model.addObject("listRole1", listRole1);
		model.setViewName("user/registration");

		return model;
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("user/registration");
		} else {
			userService.saveUser(user);
			model.addObject("msg", "User has been registered successfully!");
			model.addObject("user", new User());
			model.setViewName("user/registration");
	
		}

		return model;
	}

	@RequestMapping(value = { "/home/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Product> products = productService.getAllProduct();  

	    model.addObject("products", products); 
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.setViewName("home/home");
		return model;
	}

	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}
	
	/*ROLE*/
	  @RequestMapping(value = "addRole")  
	  public String addCatalogy(Model model) {  
	    model.addAttribute("role", new Role());  
	    return "user/addRole";  
	  }   

	  @RequestMapping(value = "saveRole", method = RequestMethod.POST)  
	  public String save(Role role) {  
	    roleService.saveRole(role);  
	    return "redirect:user/registration";  
	  } 
}
