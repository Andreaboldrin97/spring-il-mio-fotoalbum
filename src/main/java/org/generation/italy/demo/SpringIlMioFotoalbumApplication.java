package org.generation.italy.demo;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	//DIPENDENSE
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoryService categoryService;
	
	//DATI FAKER
		@Override
		public void run(String... args) throws Exception {
			
			//CATEGORY
			Category c1 = new Category("film");
			Category c2 = new Category("sport");
			Category c3 = new Category("hobby");
			Category c4 = new Category("calcio");
			Category c5 = new Category("moda");
			Category c6 = new Category("programazzione");
			
			categoryService.save(c1);
			categoryService.save(c2);
			categoryService.save(c3);
			categoryService.save(c4);
			categoryService.save(c5);
			categoryService.save(c6);
			
			//FOTO
			Foto f1 = new Foto("tiltle 1", "description 1", 
						"https://images.prismic.io/mystique/5d7c09b9-40e5-4254-ae1c-2c1cb59aa898_IMG3.jpg?auto=compress,format",
						"avventura", true , c1,c3);
			Foto f2 = new Foto("tiltle 2", null,
							"https://www.timeoutdubai.com/cloud/timeoutdubai/2021/09/14/yvA5SpUH-IMG-Worlds.jpg",
							"viaggio", true , c2,c3);
			Foto f3 = new Foto("tiltle 3", "description 3",
							"https://www.visitdubai.com/-/media/gathercontent/article/t/top-rides-at-img-worlds-of-adventure/media/top-rides-at-img-worlds-of-adventure-predator-5.jpg?rev=f1bb54a15add49a09c912eac851f4ff7&cx=0.56&cy=0.4&cw=397&ch=397",
							"divertimento", false ,c2, c6);
			
			fotoService.save(f1);
			fotoService.save(f2);
			fotoService.save(f3);
		}
}
