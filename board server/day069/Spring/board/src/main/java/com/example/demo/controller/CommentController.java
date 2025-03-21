package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private CommentService commentService;
	private AuthService authService;
	
	public CommentController(CommentService commentService, AuthService authService) {
		super();
		this.commentService = commentService;
		this.authService = authService;
	}


	@DeleteMapping("/{commentId}")
	public ResponseEntity<BaseResBody<Void>> deleteComment(@PathVariable(name = "commentId") Integer commentId){
		this.authService.autheticate();
		this.commentService.removeComment(commentId, this.authService.getAuthentication());
		return new BaseResBody<Void>(
				null,
				"comment deleted")
				.toResponse(HttpStatus.OK);
	}
}
