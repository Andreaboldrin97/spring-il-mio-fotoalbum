package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PathVariable;
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
			
			//esportiamo il costrutto delle foto
			Category category = new Category();
			model.addAttribute("category", category);
			
			List<Foto> allFoto =  fotoService.findAll();
			model.addAttribute("allFoto", allFoto);
			
			return "categoryCRUD/create";
		}
		@PostMapping("category/store")
		public String storeCategory(@Valid @ModelAttribute("category") Category category,
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
		
		//EDIT&UPDATE
		@GetMapping("category/edit/{id}")
		public String editCategory(@PathVariable("id") int id, Model model) {
					
					// selezioniamo il record con quell'id
					Optional<Category> optCategory = categoryService.findCategoryByID(id);
					Category category  = optCategory.get();
					model.addAttribute("category", category);
					
					List<Foto> allFoto =  fotoService.findAll();
					model.addAttribute("allFoto", allFoto);
					
					return "categoryCRUD/update";
				}
				@PostMapping("category/update")
				public String updateCategory(@Valid @ModelAttribute("category") Category category,
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
						return "redirect:/admin/category/edit/"  + category.getId();			
					}
					//azzeriamo la categoria prima di riassegnarlo 
					//prendiamo l'id
					Optional<Category> optCategory = categoryService.findCategoryByID(category.getId());
					Category cat  = optCategory.get();
					//azzeriamo l'ingrediente prima di salvarlo
					for (Foto foto : cat.getFoto()) {
						foto.getCategories().remove(cat);
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
				
				//DELETE
				@GetMapping("category/delete/{id}")
				public String deleteCategory(@PathVariable("id") int id) {
					
					// selezioniamo il record con quell'id
					Optional<Category> optCategory = categoryService.findCategoryByID(id);
					Category cat  = optCategory.get();
					
					//azzeriamo l'ingrediente prima di salvarlo
					for (Foto foto : cat.getFoto()) {
						foto.getCategories().remove(cat);
					}
					
					//metodo per eliminare un record
					categoryService.delete(cat);
					
					//a quale view ritorna
					return  "redirect:/allCategory";
				}
}
