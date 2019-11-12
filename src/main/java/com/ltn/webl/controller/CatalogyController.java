package com.ltn.webl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltn.webl.entity.Catalogy;
import com.ltn.webl.service.CatalogyService;

@Controller
public class CatalogyController {
	@Autowired private CatalogyService catalogyService;  

	  @RequestMapping("/catalogyList")  
	  public String index(Model model) {  
	    List<Catalogy> catalogys = catalogyService.getAllCatalogy();  

	    model.addAttribute("catalogys", catalogys);  

	    return "home/catalogy/catalogyList";  
	  }  

	  @RequestMapping(value = "addCatalogy")  
	  public String addCatalogy(Model model) {  
	    model.addAttribute("Catalogy", new Catalogy());  
	    return "home/catalogy/addCatalogy";  
	  }  

	  @RequestMapping(value = "/editCatalogy", method = RequestMethod.GET)  
	  public String editCatalogy(@RequestParam("id") Long catalogyId, Model model) {  
	    Optional<Catalogy> catalogyEdit = catalogyService.findCatalogyById(catalogyId);  
	    catalogyEdit.ifPresent(Catalogy -> model.addAttribute("Catalogy", Catalogy));  
	    return "home/Catalogy/editCatalogy";  
	  }  

	  @RequestMapping(value = "saveCatalogy", method = RequestMethod.POST)  
	  public String save(Catalogy catalogy) {  
	    catalogyService.saveCatalogy(catalogy);  
	    return "redirect:/home/home";  
	  }  

	  @RequestMapping(value = "/deleteCatalogy", method = RequestMethod.GET)  
	  public String deleteCatalogy(@RequestParam("id") Long CatalogyId, Model model) {  
	    catalogyService.deleteCatalogy(CatalogyId);  
	    return "redirect:/home/home";  
	  }  

}
