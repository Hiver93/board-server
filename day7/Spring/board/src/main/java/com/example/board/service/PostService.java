package com.example.board.service;

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
	
	public List<Post> getPostList(){
		return this.postRepository.findAll();
	}
	
	public Post getPost(long postId) {
		return this.postRepository.findById(postId).orElseThrow();
	}
	
	public void deletePost(long postId, String password) {
		if(this.postRepository.findById(postId).get().getPassword().equals(password)) {
			this.postRepository.deleteById(postId);
		}
		else {
			throw new RuntimeException();
		}
	}
	
	public void modifyPost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow();
		if(saved.getPassword().equals(post.getPassword())) {
			this.postRepository.save(post);
		}
		else {
			throw new RuntimeException();
		}
		
	}
}
