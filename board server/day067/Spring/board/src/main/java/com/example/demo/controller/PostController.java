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

import com.example.demo.BaseResBody;
import com.example.demo.dto.CommentReqDto;
import com.example.demo.dto.PostReqDto;
import com.example.demo.dto.PostResDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	private AuthService authService;
	private PostService postService;
	public PostController(AuthService authService, PostService postService) {
		super();
		this.authService = authService;
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<BaseResBody<Void>> postPost(@RequestBody @Valid PostReqDto.Create dto){
		this.postService.createPost(dto.getTitle(), dto.getContent(), dto.getPassword(), this.authService.getAuthentication());
		return new BaseResBody<Void>(null, "posted")
				.toResponsen(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<BaseResBody<PostResDto.PostList>> getPostList(Pageable pageable, @RequestParam(name = "keyword") String keyword, @RequestParam(name = "target") Set<String> target){
		var result = PostResDto.PostList.from(this.postService.getTotalSize(keyword, target), this.postService.getPostList(pageable, keyword, target));
		return new BaseResBody<>(result, "get post list")
				.toResponsen(HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<BaseResBody<PostResDto.Detail>> getPost(@PathVariable(name = "postId") Integer postId){
		var result = PostResDto.Detail.from(this.postService.getPost(postId));
		this.postService.increaseView(postId);
		return new BaseResBody<>(result, "get post")
				.toResponsen(HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<BaseResBody<Void>> deletePost(@PathVariable(name = "postId") Integer postId, @RequestBody @Valid PostReqDto.Delete dto){
		this.postService.removePost(postId, dto.getPassword(), this.authService.getAuthentication());
		return new BaseResBody<Void>(null, "post deleted")
				.toResponsen(HttpStatus.OK);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<BaseResBody<Void>> putPost(@PathVariable(name = "postId") Integer postId, @RequestBody @Valid PostReqDto.Update dto){
		this.postService.modifyPost(postId, dto.getTitle(), dto.getPassword(), this.authService.getAuthentication());
		return new BaseResBody<Void>(null, "post modified")
				.toResponsen(HttpStatus.OK);
	}
	
	@PostMapping("/{postId}/comments")
	public ResponseEntity<BaseResBody<Void>> postComment(@PathVariable(name = "postId") Integer postId, @RequestBody @Valid CommentReqDto.Create dto){
		this.authService.authenticate();
		this.postService.addComment(postId, dto.getContent(), this.authService.getAuthentication());
		return new BaseResBody<Void>(null, "commented")
				.toResponsen(HttpStatus.CREATED);
	}
	
	@PostMapping("/{postId}/likes")
	public ResponseEntity<BaseResBody<Void>> postPostLike(@PathVariable(name = "postId") Integer postId){
		this.authService.authenticate();
		this.postService.addPostLike(postId, this.authService.getAuthentication());
		return new BaseResBody<Void>(null, "liked")
				.toResponsen(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{postId}/likes")
	public ResponseEntity<BaseResBody<Void>> deletePostLike(@PathVariable(name = "postId") Integer postId){
		this.authService.authenticate();
		this.postService.removePostLike(postId, this.authService.getAuthentication());
		return new BaseResBody<Void>(null, "like canceled")
				.toResponsen(HttpStatus.OK);
	}
}
