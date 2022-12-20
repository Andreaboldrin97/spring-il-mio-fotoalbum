package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	//funzione per salvare/inserire un record
	public Category save(Category category) {
			
		//grazie all'interfaccia JpaRepository possiamo usare il method save
		return categoryRepo.save(category);
	}
	
	//funzione per prendere tutti i record
	public List<Category> findAll() {
			
		//ritorniamo una lista di record
		return categoryRepo.findAll();
	}
	
	//funzione per recuperare un record specifico in base all'id
			//utilizzeremo l'Optional<> per controllare se un valore è presente o non 
	public Optional<Category> findCategoryByID(int id){
			
	 return categoryRepo.findById(id);
	}
	
	//funzione per l'elimanazione di un record
	public void delete(Category category) {
		//grazie all'interfaccia JpaRepository possiamo usare il method delete
		categoryRepo.delete(category);
	}	
}
