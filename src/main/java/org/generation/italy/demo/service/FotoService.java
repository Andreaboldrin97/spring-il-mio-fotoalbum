package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.repo.FotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
	
	//indichiamo la dipendenza da iniettare
	@Autowired
	private FotoRepo fotoRepo;
	
	//funzione per salvare/inserire un record
	public Foto save(Foto foto) {
			
		//grazie all'interfaccia JpaRepository possiamo usare il method save
		return fotoRepo.save(foto);
	}
	
	//funzione per prendere tutti i record
	public List<Foto> findAll() {
			
		//ritorniamo una lista di record
		return fotoRepo.findAll();
	}
	
	//funzione per recuperare un record specifico in base all'id
			//utilizzeremo l'Optional<> per controllare se un valore è presente o non 
	public Optional<Foto> findFotoByID(int id){
			
		return fotoRepo.findById(id);
	}
	
	//funzione per l'elimanazione di un record
		public void delete(Foto foto) {
			//grazie all'interfaccia JpaRepository possiamo usare il method delete
			fotoRepo.delete(foto);
		}
		
	//funzione di ricerca by title
	public List<Foto> findByTitle(String title) {
		//ritorniamo una lista di record
		return fotoRepo.findBytitleContainingIgnoreCase(title);
	}
	
	//funzione di ricerca by tag
	public List<Foto> findByTag(String tag) {
		//ritorniamo una lista di record
		return fotoRepo.findBytagContainingIgnoreCase(tag);
	}
}
