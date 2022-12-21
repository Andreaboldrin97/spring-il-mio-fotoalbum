package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	//indichiamo la dipendenza da iniettare
	@Autowired
	private PhotoService PhotoService;
	

		//HOME
		//Indichiamo a quale path fa riferimento questo metodo
		@GetMapping("/")
		public String getHome() {
			//ritorniamo il file assocciato al nome "home"
			return "home";
		}
		
	//FILTERED SEARCH
		@GetMapping("/search")
		public String findByName(Model model,
									// @RequestParam per estrarre parametri di query, 
									@RequestParam(name = "query", required=false) String query) {
				
		System.err.println(query);
			//utilizziamo un ternario per verificare la presenza di una query
			List<Photo> allPhoto = query == null ? PhotoService.findAll() : PhotoService.findByTitleAndTag(query, query);
			//portiamo i record alla pagina
			model.addAttribute("allPhoto", allPhoto);
			model.addAttribute("query", query);
				
			return "searching";
		}
		
}
