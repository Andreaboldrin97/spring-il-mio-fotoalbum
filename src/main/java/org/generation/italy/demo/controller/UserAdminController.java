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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("useradmin")
public class UserAdminController {
	//indichiamo la dipendenza da iniettare
		@Autowired
		private FotoService fotoService;
		
		@Autowired
		private CategoryService categoryService;
	
	//ALL FREE PATH FOTO	
		
	//INDEX FOTO
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/allFoto")
	public String getFoto(Model model) {
		//assegnamo ad un lista i record del db
		List<Foto> allFoto = fotoService.findAll();
					
		model.addAttribute("allFoto", allFoto);
					
		//a quale view fa riferimento
		return "fotoCRUD/index";
	}
	
	//SHOW FOTO
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/show/foto/{id}")
	public String getFotoById(@PathVariable("id") int id, Model model) {
		// selezioniamo il record con quell'id
		Optional<Foto> optFoto = fotoService.findFotoByID(id);
		Foto foto = optFoto.get();					
		model.addAttribute("foto", foto);
						
		//a quale view fa riferimento
		return "fotoCRUD/show";
	}
	
	//ALL FREE PATH CATEGORY	
	
	//INDEX CATEGORY
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/allCategory")
	public String getCategory(Model model) {
		//assegnamo ad un lista i record del db
		List<Category> categories = categoryService.findAllFoto();
						
		model.addAttribute("categories", categories);
						
		//a quale view fa riferimento
		return "categoryCRUD/index";
	}
	
}
