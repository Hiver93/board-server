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

	private AuthService authService;
	private CommentService commentService;	
	public CommentController(AuthService authService, CommentService commentService) {
		super();
		this.authService = authService;
		this.commentService = commentService;
	}


	@DeleteMapping("/{userId}")
	public ResponseEntity<BaseResBody<Void>> deleteComment(@PathVariable(name = "commentId") Integer commentId){
		this.commentService.removeComment(commentId, this.authService.authenticate());
		return new BaseResBody<Void>(null, "comment deleted")
				.toResponse(HttpStatus.OK);
	}
}
