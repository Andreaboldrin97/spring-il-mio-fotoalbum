package org.generation.italy.demo.controller.api;

import java.util.Set;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/comment")
@CrossOrigin("*")
public class CommentApiController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/by/foto/{id}")
	public Set<Comment> getCommentByFotoId(@PathVariable("id") int id){
		
		Foto foto = fotoService.findFotoByID(id).get();
		return  foto.getComments();
	}
	
	@PostMapping("/add/by/foto/{id}")
	public Comment addCommentByFotoId(@PathVariable("id") int id , @Valid @RequestBody Comment comment) {
		
		//prendo l'elemento by id
		Foto foto = fotoService.findFotoByID(id).get();
		
		Comment newComment = new Comment(comment.getName(), comment.getText(), foto);
		
		commentService.save(newComment);
		
		return newComment;
	}
}
