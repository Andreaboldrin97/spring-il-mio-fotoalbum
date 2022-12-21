package org.generation.italy.demo;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.FotoService;
import org.generation.italy.demo.service.RoleService;
import org.generation.italy.demo.service.UserService;
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
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	//DATI FAKER
		@Override
		public void run(String... args) throws Exception {
			
			//ROLE
			Role user = new Role("user");
			Role admin= new Role("admin");
			
			roleService.save(user);
			roleService.save(admin);
			
			//USER
			User user1 = new User("user","{noop}user",user);
			User admin1 = new User("admin","{noop}admin",admin);
			User userAdmin1 = new User("useradmin", "{noop}useradmin", user, admin);
			
			userService.save(user1);
			userService.save(admin1);
			userService.save(userAdmin1);
			
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
			Foto f4 = new Foto("tiltle 4", "description 14", 
							"https://images.prismic.io/mystique/5d7c09b9-40e5-4254-ae1c-2c1cb59aa898_IMG3.jpg?auto=compress,format",
							"avventura", true , c1,c3);
			Foto f5 = new Foto("tiltle 5", null,
							"https://www.timeoutdubai.com/cloud/timeoutdubai/2021/09/14/yvA5SpUH-IMG-Worlds.jpg",
							"viaggio", true , c2,c3);
			Foto f6 = new Foto("tiltle 6", "description 16", 
							"https://images.prismic.io/mystique/5d7c09b9-40e5-4254-ae1c-2c1cb59aa898_IMG3.jpg?auto=compress,format",
							"avventura", true , c1,c3);
			Foto f7 = new Foto("tiltle 7", null,
							"https://www.timeoutdubai.com/cloud/timeoutdubai/2021/09/14/yvA5SpUH-IMG-Worlds.jpg",
							"viaggio", true , c2,c3);
				
			fotoService.save(f1);
			fotoService.save(f2);
			fotoService.save(f3);
			fotoService.save(f4);
			fotoService.save(f5);
			fotoService.save(f6);
			fotoService.save(f7);
			
			//COMMENT
			Comment com1 = new Comment("poyo1", "Commento 1",f5);
			Comment com2 = new Comment("poyo2", "Commento 2",f5);
			
			commentService.save(com1);
			commentService.save(com2);
		}
}
