package org.generation.italy.demo.controller.api;

import java.util.List;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/Photo")
@CrossOrigin("*")
public class PhotoApiController {
	//indichiamo la dipendenza da iniettare
	@Autowired
	private PhotoService PhotoService;
	
	@GetMapping("/all")
	public List<Photo> getAll() {
		return PhotoService.findAll();
	}
	
	@GetMapping("/search/{query}")
	public List<Photo> getSearchPhoto(@PathVariable("query") String query) {
		 System.err.println(query);
		//utilizziamo un ternario per verificare la presenza di una query
		List<Photo> allPhoto = query == null ? PhotoService.findAll() : PhotoService.findByTitleAndTag(query, query);
		return allPhoto ;
	}
}
