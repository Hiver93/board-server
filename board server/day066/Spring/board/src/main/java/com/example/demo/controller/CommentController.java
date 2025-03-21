package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.dto.UserReqDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/commments")
public class CommentController {

	private AuthService authService;
	private CommentService commentService;	
	public CommentController(AuthService authService, CommentService commentService) {
		super();
		this.authService = authService;
		this.commentService = commentService;
	}


	@DeleteMapping("/{commentId}")
	public ResponseEntity<BaseResBody<Void>> deleteComment(@PathVariable(name = "commentId") Integer commentId){
		this.authService.authenticate();
		this.commentService.removeComment(commentId, this.authService.getAuthentication());
		return new BaseResBody<Void>(null, "comment deleted")
				.toResponse(HttpStatus.OK);
	}
}
