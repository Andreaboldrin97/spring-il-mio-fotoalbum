package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.repo.CategoryRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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
	
	//funzione per le foto correlate
	
	//usiamo questa annotation per mantere il canele aperto tra il db e le query
	@Transactional
	public List<Category> findAllFoto() {
		
		//inserisco in una lista tutti gli ingredienti
		List<Category> categories = categoryRepo.findAll();	
		
		for(Category category : categories) {		
			//usiamo questa annotazione per creare la query al db
			Hibernate.initialize(category.getFoto());
		}		
		//ritorno la stista con la join
		return categories;
	}
}
