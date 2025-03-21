package com.example.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.Post;
import com.example.board.dto.PostRequestDto;
import com.example.board.service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	public void postPost(@RequestBody PostRequestDto postRequestDto) {
		this.postService.createPost(new Post(postRequestDto.getTitle(), postRequestDto.getContent()));
	}
	
	@GetMapping
	public List<Post> getPostList(){
		return this.postService.getPostList();
	}
	
	@GetMapping("/{postId}")
	public Post getPost(@PathVariable(name = "postId") long postId) {
		return this.postService.getPost(postId);
	}
	
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable(name="postId") long postId) {
		this.postService.deletePost(postId);
	}
	
	@PutMapping("/{postId}")
	public void putPost(@PathVariable(name = "postId") long postId,@RequestBody PostRequestDto postRequestDto) {
		Post post = new Post(postRequestDto.getTitle(), postRequestDto.getContent());
		post.setId(postId);
		this.postService.modifyPost(post);
	}
}
