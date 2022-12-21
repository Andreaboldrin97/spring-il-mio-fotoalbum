package org.generation.italy.demo;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.PhotoService;
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
	private PhotoService PhotoService;
	
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
			Category c4 = new Category("programazzione");
			Category c5 = new Category("arte");
			Category c6 = new Category("cani");
			Category c7 = new Category("family");
			Category c8 = new Category("giappone");
			Category c9 = new Category("cina");
			
			categoryService.save(c1);
			categoryService.save(c2);
			categoryService.save(c3);
			categoryService.save(c4);
			categoryService.save(c5);
			categoryService.save(c6);
			categoryService.save(c7);
			categoryService.save(c8);
			categoryService.save(c9);
			
			//Photo
			Photo f1 = new Photo("my family", "io e la mia famiglia", 
						"https://blog.ferplast.com/wp-content/uploads/2020/09/bassotto-arlecchino-prezzo-adozione-1024x683.jpg",
						"avventura", true, c1,c7);
			Photo f2 = new Photo("Giappone lovers", null,
							"https://www.analisidellopera.it/wp-content/uploads/2019/10/Hokusai_La_grande_onda_di_Kanagawa.jpg",
							"hokusai", true, c5,c8);
			Photo f3 = new Photo("disneyland", "che emozione...",
							"https://i0.wp.com/www.badtaste.it/cinema/wp-content/uploads/sites/1/2021/03/disneyland-paris.jpeg?fit=1200%2C600&quality=85&strip=all&ssl=1",
							"divertimento", false, c3);
			Photo f4 = new Photo("spider move", "che film ragazzi....", 
							"https://images.prismic.io/mystique/5d7c09b9-40e5-4254-ae1c-2c1cb59aa898_IMG3.jpg?auto=compress,format",
							"marvel", true, c1,c3);
			Photo f5 = new Photo("io quando riprendo VSC dopo aver usato eclipse", null,
							"https://www.bassottotedesco.com/wp-content/uploads/2022/01/bassotto-arlecchino-nero.jpg",
							"gioia", true, c6, c4, c3);
			Photo f6 = new Photo("cina lover", null,
					"https://www.timeoutdubai.com/cloud/timeoutdubai/2021/09/14/yvA5SpUH-IMG-Worlds.jpg",
					"viaggio", true, c2, c9);
		
			PhotoService.save(f1);
			PhotoService.save(f2);
			PhotoService.save(f3);
			PhotoService.save(f4);
			PhotoService.save(f5);
			PhotoService.save(f6);
			
			//COMMENT
			Comment com1 = new Comment("poyo1", "Commento 1",f5);
			Comment com2 = new Comment("poyo2", "Commento 2",f5);
			
			commentService.save(com1);
			commentService.save(com2);
		}
}
