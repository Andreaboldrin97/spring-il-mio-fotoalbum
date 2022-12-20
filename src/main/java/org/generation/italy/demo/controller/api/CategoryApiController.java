package org.generation.italy.demo.controller.api;

import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/category")
@CrossOrigin("*")
public class CategoryApiController {
	//indichiamo la dipendenza da iniettare
	@Autowired
	private FotoService fotoService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/all")
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("/by/foto/{id}")
	public Set<Category> getCategoryByFotoId(@PathVariable("id") int id) {
		
		Foto foto = fotoService.findFotoByID(id).get();
		return  foto.getCategories();
	}
}

