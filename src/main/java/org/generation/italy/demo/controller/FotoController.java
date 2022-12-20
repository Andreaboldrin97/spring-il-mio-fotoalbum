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



//indichiamo che qesta classe ci servirà da controller
@Controller
@RequestMapping("admin")
public class FotoController {
	
	//indichiamo la dipendenza da iniettare
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	
	//CREATE&STORE
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/foto/create")
	public String createFoto(Model model) {
		
		//esportiamo il costruttu delle foto
		Foto foto = new Foto();
		model.addAttribute("foto", foto);
		
		List<Category> categories =  categoryService.findAll();
		model.addAttribute("categories", categories);
		
		//a quale view fa riferimento
		return "fotoCRUD/create";
	}
	@PostMapping("foto/store")
	public String storeFoto(@Valid @ModelAttribute("foto") Foto foto,
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
			return "redirect:/admin/foto/create";
		}
		
		//metodo per salvare un record
		fotoService.save(foto);
		
		//a quale view ritorna
		return "redirect:/allFoto";
	}
	
	//EDIT&UPDATE
	//Indichiamo a quale path fa riferimento questo metodo
			@GetMapping("foto/edit/{id}")
			public String editFoto(@PathVariable("id") int id, Model model) {
				
				// selezioniamo il record con quell'id
				Optional<Foto> optFoto = fotoService.findFotoByID(id);
				Foto foto = optFoto.get();					
				model.addAttribute("foto", foto);
				
				
				List<Category> categories =  categoryService.findAll();
				model.addAttribute("categories", categories);
				
				//a quale view fa riferimento
				return "fotoCRUD/update";
			}
			@PostMapping("foto/update")
			public String updateFoto(@Valid @ModelAttribute("foto") Foto foto,
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
					return "redirect:/admin/foto/edit/" + foto.getId();			
				}
				
				//metodo per salvare un record
				fotoService.save(foto);
				
				//a quale view ritorna
				return "redirect:/allFoto";
			}
			
			//DELETE 
			//Indichiamo a quale path fa riferimento questo metodo
			@GetMapping("foto/delete/{id}")
			public String deleteFoto(@PathVariable("id") int id) {
				
				// selezioniamo il record con quell'id
				Optional<Foto> optFoto = fotoService.findFotoByID(id);
				Foto foto = optFoto.get();					
				
				//metodo per eliminare un record
				fotoService.delete(foto);
				
				//a quale view ritorna
				return  "redirect:/allFoto";
			}
}
