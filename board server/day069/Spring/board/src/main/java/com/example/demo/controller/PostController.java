package com.example.demo.controller;

import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.dto.CommentReqDto;
import com.example.demo.dto.PostReqDto;
import com.example.demo.dto.PostResDto;
import com.example.demo.facade.PostFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostFacade postFacade;

	public PostController(PostFacade postFacade) {
		super();
		this.postFacade = postFacade;
	}
	
	@PostMapping
	public ResponseEntity<BaseResBody<Void>> postPost(@RequestBody @Valid PostReqDto.Create dto){
		this.postFacade.createPost(dto);
		return new BaseResBody<Void>(null, "posted")
				.toResponse(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<BaseResBody<PostResDto.PostList>> getPostList(Pageable pageable, @RequestParam(name = "keyword") String keyword, @RequestParam(name = "target") Set<String> target){
		return new BaseResBody<>(
				this.postFacade.getPostList(pageable, keyword, target), 
				"get post list")
				.toResponse(HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<BaseResBody<PostResDto.Detail>> getPost(@PathVariable(name = "postId") Integer postId){
		return new BaseResBody<>(
				this.postFacade.readPost(postId), 
				"get post")
				.toResponse(HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<BaseResBody<Void>> deletePost(@PathVariable(name = "postId") Integer postId, @RequestBody @Valid PostReqDto.Delete dto){
		this.postFacade.removePost(postId, dto); 
		return new BaseResBody<Void>(
				null,
				"post deleted")
				.toResponse(HttpStatus.OK);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<BaseResBody<Void>> putPost(@PathVariable(name = "postId") Integer postId, @RequestBody @Valid PostReqDto.Put dto){
		this.postFacade.modifyPost(postId, dto); 
		return new BaseResBody<Void>(
				null,
				"post modified")
				.toResponse(HttpStatus.OK);
	}
	
	@PostMapping("/{postId}/comments")
	public ResponseEntity<BaseResBody<Void>> postComment(@PathVariable(name = "postId") Integer postId, @RequestBody @Valid CommentReqDto.Create dto){
		this.postFacade.addComment(postId, dto);
		return new BaseResBody<Void>(
				null,
				"commented")
				.toResponse(HttpStatus.CREATED);
	}
	
	@PostMapping("/{postId}/likes")
	public ResponseEntity<BaseResBody<Void>> postPostLike(@PathVariable(name = "postId") Integer postId){
		this.postFacade.addPostLike(postId);
		return new BaseResBody<Void>(
				null,
				"liked")
				.toResponse(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{postId}/likes")
	public ResponseEntity<BaseResBody<Void>> deletePostLike(@PathVariable(name = "postId") Integer postId){
		this.postFacade.removePostLike(postId);
		return new BaseResBody<Void>(
				null,
				"liked")
				.toResponse(HttpStatus.OK);
	}
}
