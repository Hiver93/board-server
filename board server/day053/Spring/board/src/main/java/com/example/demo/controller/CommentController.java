package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseBody;
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
	public ResponseEntity<BaseBody<Void>> postComment(@RequestBody @Valid CommentReqDto.Create dto){
		this.commentService.createComment(dto.to(this.authService.authenticate()));
		
		return new BaseBody<Void>(null, "commented")
				.toResponse(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<BaseBody<Void>> login(@PathVariable(name = "commentId") Integer commentId){
		this.commentService.removeComment(commentId, this.authService.authenticate());
		return new BaseBody<Void>(null, "comment deleted")
				.toResponse(HttpStatus.ACCEPTED);
	}
}
