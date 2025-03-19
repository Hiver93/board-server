package com.example.board.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.domain.Post;
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
	
	public List<Post> getPostList() {
		return this.postRepository.findAll();
	}
	
	public Post getPost(long postId) {
		return this.postRepository.findById(postId);
	}
	
	public void deletePost(long postId) {
		this.postRepository.deleteById(postId);
	}
	
	public void modifyPost(Post post) {
		this.postRepository.update(post);
	}
}
