package org.generation.italy.demo.repo;

import java.util.List;

import org.generation.italy.demo.pojo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepo  extends JpaRepository<Foto, Integer>{
	//metodo custum per la ricerca
		//il nome del metodo ha le parole chiavi per effetuare la chiamata sql 
		public List<Foto> findByTitleContainingOrTagContaining(String title, String tag);
		
}
