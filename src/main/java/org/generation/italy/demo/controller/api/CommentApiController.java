package org.generation.italy.demo.controller.api;

import java.util.Set;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.PhotoService;
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
	private PhotoService PhotoService;
	
	@GetMapping("/by/Photo/{id}")
	public Set<Comment> getCommentByPhotoId(@PathVariable("id") int id){
		
		Photo Photo = PhotoService.findPhotoByID(id).get();
		return  Photo.getComments();
	}
	
	@PostMapping("/add/by/Photo/{id}")
	public Comment addCommentByPhotoId(@PathVariable("id") int id , @Valid @RequestBody Comment comment) {
		
		//prendo l'elemento by id
		Photo Photo = PhotoService.findPhotoByID(id).get();
		
		Comment newComment = new Comment(comment.getName(), comment.getText(), Photo);
		
		commentService.save(newComment);
		
		return newComment;
	}
}
