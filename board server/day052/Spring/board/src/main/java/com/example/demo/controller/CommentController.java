package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.dto.CommentReqDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;

import jakarta.validation.Valid;

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
	
	@PostMapping
	public ResponseEntity<BaseResBody> postComment(@RequestBody @Valid CommentReqDto.Create dto){
		this.commentService.createComment(dto.to(authService.authenticate()));
		return BaseResBody.of(HttpStatus.CREATED,  "commented");
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<BaseResBody> deleteComment(@PathVariable(name = "commentId") Integer commentId){
		this.commentService.removeComment(commentId, this.authService.authenticate());
		return BaseResBody.of(HttpStatus.ACCEPTED,  "comment deleted");
	}
}
