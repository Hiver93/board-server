package com.example.demo.facade;

import org.springframework.stereotype.Component;

import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;

@Component
public class CommentFacade {

	private AuthService authService;
	private CommentService commentService;
	public CommentFacade(AuthService authService, CommentService commentService) {
		super();
		this.authService = authService;
		this.commentService = commentService;
	}
	
	public void removeComment(Integer commentId) {
		this.commentService.removeComment(commentId, this.authService.getAuthentication());
	}
}
