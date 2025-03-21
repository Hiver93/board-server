package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.facade.CommentFacade;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private CommentFacade commentFacade;

	public CommentController(CommentFacade commentFacade) {
		super();
		this.commentFacade = commentFacade;
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<BaseResBody<Void>> deleteComment(@PathVariable(name = "commentId") Integer commnetId){
		this.commentFacade.removeComment(commnetId);
		return new BaseResBody<Void>(
				null, 
				"comment deleted")
				.toResponse(HttpStatus.OK);
	}
}
