package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
	public void createComment(Comment comment) {
		this.commentRepository.save(comment);
	}
}
