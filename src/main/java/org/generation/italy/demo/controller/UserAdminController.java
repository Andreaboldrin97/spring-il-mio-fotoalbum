package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("useradmin")
public class UserAdminController {
	//indichiamo la dipendenza da iniettare
		@Autowired
		private PhotoService PhotoService;
		
		@Autowired
		private CategoryService categoryService;
	
	//ALL FREE PATH Photo	
		
	//INDEX Photo
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/allPhoto")
	public String getPhoto(Model model) {
		//assegnamo ad un lista i record del db
		List<Photo> allPhoto = PhotoService.findAll();
					
		model.addAttribute("allPhoto", allPhoto);
					
		//a quale view fa riferimento
		return "PhotoCRUD/index";
	}
	
	//SHOW Photo
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/show/Photo/{id}")
	public String getPhotoById(@PathVariable("id") int id, Model model) {
		// selezioniamo il record con quell'id
		Optional<Photo> optPhoto = PhotoService.findPhotoByID(id);
		Photo Photo = optPhoto.get();					
		model.addAttribute("Photo", Photo);
						
		//a quale view fa riferimento
		return "PhotoCRUD/show";
	}
	
	//ALL FREE PATH CATEGORY	
	
	//INDEX CATEGORY
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/allCategory")
	public String getCategory(Model model) {
		//assegnamo ad un lista i record del db
		List<Category> categories = categoryService.findAllPhoto();
						
		model.addAttribute("categories", categories);
						
		//a quale view fa riferimento
		return "categoryCRUD/index";
	}
	
}
