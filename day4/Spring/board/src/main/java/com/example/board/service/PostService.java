package com.example.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.entity.Post;
import com.example.board.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public void createPost(Post post) {
		this.postRepository.save(post);
	}
	
	public List<Post> getAll(){
		return this.postRepository.findAll();
	}
	
	public Post getById(long postId) {
		return this.postRepository.findById(postId);
	}
	
	public void deleteById(long postId) {
		this.postRepository.deleteById(postId);
	}
	
	public void modifyPost(Post post) {
		this.postRepository.updatePost(post);
	}
}
