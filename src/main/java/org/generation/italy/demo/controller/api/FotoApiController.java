package org.generation.italy.demo.controller.api;

import java.util.List;

import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/foto")
@CrossOrigin("*")
public class FotoApiController {
	//indichiamo la dipendenza da iniettare
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/all")
	public List<Foto> getAll() {
		return fotoService.findAll();
	}
	
	@GetMapping("/search/{query}")
	public List<Foto> getSearchPhoto(@PathVariable("query") String query) {
		 System.err.println(query);
		//utilizziamo un ternario per verificare la presenza di una query
		List<Foto> allFoto = query == null ? fotoService.findAll() : fotoService.findByTitleAndTag(query, query);
		return allFoto ;
	}
}
