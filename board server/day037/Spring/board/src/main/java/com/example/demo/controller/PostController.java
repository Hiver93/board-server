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
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.PostReqDto;
import com.example.demo.dto.PostResDto;
import com.example.demo.service.PostService;
import com.example.demo.util.AuthenticationUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<BaseResBody> postPost(@RequestBody @Valid PostReqDto.Create dto){
		this.postService.createPost(dto.to(AuthenticationUtil.getUserId()));
		return BaseResBody.of(HttpStatus.CREATED, "posted");
	}
	
	@GetMapping
	public ResponseEntity<BaseResBody> getPostList(Pageable pageable, @RequestParam(name="keyword") String keyword, @RequestParam(name="target") Set<String> targets){
		var result = this.postService.getPostList(pageable, keyword, targets).stream()
				.map(PostResDto::from)
				.toList();
		return BaseResBody.of(HttpStatus.OK, result, "get post list");
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<BaseResBody> getPost(@PathVariable(name="postId") Integer postId){
		var result = PostResDto.from(this.postService.getPost(postId));
		return BaseResBody.of(HttpStatus.OK, result, "get post");
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<BaseResBody> deletePost(@PathVariable(name="postId") Integer postId){
		this.postService.deletePost(Post.builder().id(postId).user(User.builder().id(AuthenticationUtil.getUserId()).build()).build());
		return BaseResBody.of(HttpStatus.OK, "deleted");
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<BaseResBody> putPost(@PathVariable(name="postId") Integer postId, @RequestBody @Valid PostReqDto.Update dto){
		this.postService.modifyPost(dto.to(AuthenticationUtil.getUserId(), postId));
		return BaseResBody.of(HttpStatus.OK, "updated");
	}
	
	@GetMapping("/total-size")
	public ResponseEntity<BaseResBody> getTotalSize(@RequestParam(name="keyword") String keyword, @RequestParam(name="target") Set<String> targets){
		var result = this.postService.getTotalSize(keyword, targets);
		return BaseResBody.of(HttpStatus.OK, result, "get total size");
	}
}
