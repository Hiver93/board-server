package com.example.demo.facade;

import org.springframework.stereotype.Component;

import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;

@Component
public class CommentFacade {

	private CommentService commentService;
	private AuthService authService;
	public CommentFacade(CommentService commentService, AuthService authService) {
		super();
		this.commentService = commentService;
		this.authService = authService;
	}
	
	public void removeComment(Integer commentId) {
		this.authService.authenticate();
		this.commentService.removeComment(commentId, this.authService.getAuthenticateion());
	}
}
