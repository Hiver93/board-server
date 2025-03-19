package com.example.demo.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.service.AuthService;
import com.example.demo.service.CommentServicie;

@Component
public class CommentFacade {

	private CommentServicie commentServicie;
	private AuthService authService;
	public CommentFacade(CommentServicie commentServicie, AuthService authService) {
		super();
		this.commentServicie = commentServicie;
		this.authService = authService;
	}
	
	@Transactional
	public void removeComment(Integer commentId) {
		this.authService.authenticate();
		this.commentServicie.removeComment(commentId, this.authService.getAuthentication());
	}
}
