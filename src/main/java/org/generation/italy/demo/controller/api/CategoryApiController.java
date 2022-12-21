package org.generation.italy.demo.controller.api;

import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.PhotoService;
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
	private PhotoService PhotoService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/all")
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("/by/Photo/{id}")
	public Set<Category> getCategoryByPhotoId(@PathVariable("id") int id) {
		
		Photo Photo = PhotoService.findPhotoByID(id).get();
		return  Photo.getCategories();
	}
}

