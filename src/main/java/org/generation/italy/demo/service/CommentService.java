package org.generation.italy.demo.service;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	public Comment save(Comment comment) {
		return commentRepo.save(comment);
	}
}
