package com.example.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.domain.Post;
import com.example.board.domain.repository.PostRepository;

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
	
	public void deletePost(long postId) {
		this.postRepository.deleteById(postId);
	}
	
	public void modifyPost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow();
		saved.setTitle(post.getTitle());
		saved.setContent(post.getContent());
		this.postRepository.save(saved);
	}
}
