package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;



@Controller
@RequestMapping("admin")
public class CategoryController {

	//indichiamo la dipendenza da iniettare
	@Autowired
	private FotoService fotoService;
		
	@Autowired
	private CategoryService categoryService;
	
	//CREATE&STORE
		@GetMapping("/category/create")
		public String createCategory(Model model) {
			
			//esportiamo il costruttu delle foto
			Category category = new Category();
			model.addAttribute("category", category);
			
			List<Foto> allFoto =  fotoService.findAll();
			model.addAttribute("allFoto", allFoto);
			
			return "categoryCRUD/create";
		}
		@PostMapping("category/store")
		public String storeIngredient(@Valid @ModelAttribute("category") Category category,
				//Intergaccia per la registrazione degli errori 
				BindingResult bindingResult, 
				//Interfaccia secondaria di Model per passare attributi
				RedirectAttributes redirectAttributes) {

			//veriafichiamo la presenza di errori nella compilazione dei campi del form
			//hasErrors() ci ritorna un valore booleano sulla presenza o no di errori
			if(bindingResult.hasErrors()) {
				
				//riportiamo gli errori all'interno della view indicata
				redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());				
				//ritorniamo al form con gli errori se i dati sono errati
				return "redirect:/admin/category/create";
			
			}
			//salvataggio inverso
			//metodo per otterere le foto inserite
			List<Foto> allFotoChoise =  category.getFoto();
			for (Foto foto : allFotoChoise ) {
				//inserisco le categorie da salvare
				 foto.getCategories().add(category);
				
			}
			
			//metodo per salvare un record
			categoryService.save(category);
			
			//a quale view ritorna
			return "redirect:/allCategory";
		}
}
