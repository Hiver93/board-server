package com.example.board.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	public List<Post> getPostList(Pageable pageable){
		return this.postRepository.findAll(pageable).getContent();
	}
	
	public Post getPost(long postId) {
		return this.postRepository.findById(postId).orElseThrow();
	}
	
	public void deletePost(long postId, String password) {
		Post saved = this.postRepository.findById(postId).orElseThrow();
		if(!saved.getPassword().equals(password)) {
			throw new RuntimeException();
		}
		this.postRepository.delete(saved);
	}
	
	public void modifyPost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow();
		if(!saved.getPassword().equals(post.getPassword())) {
			throw new RuntimeException();
		}
		saved.setTitle(post.getTitle());
		saved.setContent(post.getContent());
		this.postRepository.save(saved);
	}
}
