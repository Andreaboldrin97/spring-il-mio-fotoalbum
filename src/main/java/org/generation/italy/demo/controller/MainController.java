package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	//indichiamo la dipendenza da iniettare
		@Autowired
		private FotoService fotoService;
		
		@Autowired
		private CategoryService categoryService;
		
	//INDEX 
	//Indichiamo a quale path fa riferimento questo metodo
	@GetMapping("/allFoto")
	public String getPizze(Model model) {
		//assegnamo ad un lista i record del db
		List<Foto> allFoto = fotoService.findAll();
					
		model.addAttribute("allFoto", allFoto);
					
		//a quale view fa riferimento
		return "fotoCRUD/index";
	}
}
