package org.generation.italy.demo.repo;

import java.util.Optional;

import org.generation.italy.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	//funzione custom per cercare byUsername
	Optional<User> findByUserName(String username);

}
