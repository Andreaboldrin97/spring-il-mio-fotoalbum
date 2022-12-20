package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	//indichiamo la dipendenza da iniettare
	@Autowired
	private FotoService fotoService;
		
	//FILTERED SEARCH
		@GetMapping("/search")
		public String findByName(Model model,
									// @RequestParam per estrarre parametri di query, 
									@RequestParam(name = "query", required=false) String query) {
				
		System.err.println(query);
			//utilizziamo un ternario per verificare la presenza di una query
			List<Foto> allFoto = query == null ? fotoService.findAll() : fotoService.findByTitleAndTag(query, query);
			//portiamo i record alla pagina
			model.addAttribute("allFoto", allFoto);
			model.addAttribute("query", query);
				
			return "searching";
		}
}
