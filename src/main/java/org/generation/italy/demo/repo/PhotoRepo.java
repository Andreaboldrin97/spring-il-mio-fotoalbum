package org.generation.italy.demo.repo;

import java.util.List;

import org.generation.italy.demo.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo  extends JpaRepository<Photo, Integer>{
	//metodo custum per la ricerca
		//il nome del metodo ha le parole chiavi per effetuare la chiamata sql 
		public List<Photo> findByTitleContainingOrTagContaining(String title, String tag);
		//metodo per ricevere solo le foto visibili
		public List<Photo> findByIsVisibleTrue();
		
}
