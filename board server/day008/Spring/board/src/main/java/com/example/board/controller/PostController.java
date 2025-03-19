package com.example.board.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.PostRequestDto;
import com.example.board.domain.Post;
import com.example.board.service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins="*")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	public void postPost(@RequestBody PostRequestDto.Post requestDto) {
		Post post = new Post();
		post.setTitle(requestDto.getTitle());
		post.setContent(requestDto.getContent());
		post.setPassword(requestDto.getPassword());
		this.postService.createPost(post);
	}
	
	@GetMapping
	public List<Post> getPostList(Pageable pageable){
		return this.postService.getPostList(pageable);
	}
	
	@GetMapping("/{postId}")
	public Post getPost(@PathVariable(name = "postId") long postId) {
		return this.postService.getPost(postId);
	}
	
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable(name="postId")long postId,@RequestBody PostRequestDto.Delete requestDto) {
		this.postService.deletePost(postId, requestDto.getPassword());
	}
	
	@PutMapping("/{postId}")
	public void putPost(@PathVariable(name="postId")long postId,@RequestBody PostRequestDto.Post requestDto) {
		Post post = new Post();
		post.setId(postId);
		post.setTitle(requestDto.getTitle());
		post.setContent(requestDto.getContent());
		post.setPassword(requestDto.getPassword());
		this.postService.modifyPost(post);
	}
}
