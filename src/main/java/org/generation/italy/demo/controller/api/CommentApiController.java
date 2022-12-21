package org.generation.italy.demo.controller.api;

import java.util.Set;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/comment")
@CrossOrigin("*")
public class CommentApiController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FotoService fotoService;
	
	public Set<Comment> getCommentByFotoId(@PathVariable("id") int id){
		
		Foto foto = fotoService.findFotoByID(id).get();
		return  foto.getComments();
	}
}
