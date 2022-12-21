package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	//indichiamo la dipendenza da iniettare
	@Autowired
	private PhotoRepo PhotoRepo;
	
	//funzione per salvare/inserire un record
	public Photo save(Photo Photo) {
			
		//grazie all'interfaccia JpaRepository possiamo usare il method save
		return PhotoRepo.save(Photo);
	}
	
	//funzione per prendere tutti i record
	public List<Photo> findAll() {
			
		//ritorniamo una lista di record
		return PhotoRepo.findAll();
	}
	
	//funzione per recuperare un record specifico in base all'id
			//utilizzeremo l'Optional<> per controllare se un valore è presente o non 
	public Optional<Photo> findPhotoByID(int id){
			
		return PhotoRepo.findById(id);
	}
	
	//funzione per l'elimanazione di un record
		public void delete(Photo Photo) {
			//grazie all'interfaccia JpaRepository possiamo usare il method delete
			PhotoRepo.delete(Photo);
		}
		
	//funzione di ricerca by title
	public List<Photo> findByTitleAndTag(String title, String tag) {
		//ritorniamo una lista di record
		return PhotoRepo.findByTitleContainingOrTagContaining(title, tag);
	}
	
	
}
