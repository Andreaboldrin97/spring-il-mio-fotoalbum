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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;



//indichiamo che qesta classe ci servirà da controller
@Controller
@RequestMapping("admin")
public class PhotoController {
	
	//indichiamo la dipendenza da iniettare
	@Autowired
	private PhotoService PhotoService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	
	//CREATE&STORE
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/Photo/create")
	public String createPhoto(Model model) {
		
		//esportiamo il costruttu delle Photo
		Photo Photo = new Photo();
		model.addAttribute("Photo", Photo);
		
		List<Category> categories =  categoryService.findAll();
		model.addAttribute("categories", categories);
		
		//a quale view fa riferimento
		return "PhotoCRUD/create";
	}
	@PostMapping("Photo/store")
	public String storePhoto(@Valid @ModelAttribute("Photo") Photo Photo,
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
			return "redirect:/admin/Photo/create";
		}
		
		//metodo per salvare un record
		PhotoService.save(Photo);
		
		//a quale view ritorna
		return "redirect:/useradmin/allPhoto";
	}
	
	//EDIT&UPDATE
	//Indichiamo a quale path fa riferimento questo metodo
			@GetMapping("Photo/edit/{id}")
			public String editPhoto(@PathVariable("id") int id, Model model) {
				
				// selezioniamo il record con quell'id
				Optional<Photo> optPhoto = PhotoService.findPhotoByID(id);
				Photo Photo = optPhoto.get();					
				model.addAttribute("Photo", Photo);
				
				
				List<Category> categories =  categoryService.findAll();
				model.addAttribute("categories", categories);
				
				//a quale view fa riferimento
				return "PhotoCRUD/update";
			}
			@PostMapping("Photo/update")
			public String updatePhoto(@Valid @ModelAttribute("Photo") Photo Photo,
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
					return "redirect:/admin/Photo/edit/" + Photo.getId();			
				}
				
				//metodo per salvare un record
				PhotoService.save(Photo);
				
				//a quale view ritorna
				return "redirect:/useradmin/allPhoto";
			}
			
			//DELETE 
			//Indichiamo a quale path fa riferimento questo metodo
			@GetMapping("Photo/delete/{id}")
			public String deletePhoto(@PathVariable("id") int id) {
				
				// selezioniamo il record con quell'id
				Optional<Photo> optPhoto = PhotoService.findPhotoByID(id);
				Photo Photo = optPhoto.get();					
				
				//metodo per eliminare un record
				PhotoService.delete(Photo);
				
				//a quale view ritorna
				return  "redirect:/useradmin/allPhoto";
			}
}
