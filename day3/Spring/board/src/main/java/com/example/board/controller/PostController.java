package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.Post;
import com.example.board.service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	public void createPost(Post post) {
		this.postService.createPost(post);
	}
	
	@GetMapping
	public List<Post> getAll(){
		return this.postService.getAll();
	}
	
	@GetMapping("/{postId}")
	public Post getById(@PathVariable(name = "postId") long id) {
		return this.postService.getOneById(id);
	}
	
	@DeleteMapping("/{postId}")
	public void deleteById(@PathVariable(name="postId") long id) {
		this.postService.deleteById(id);
	}
}
