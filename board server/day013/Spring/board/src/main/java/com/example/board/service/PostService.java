package com.example.board.service;

import java.util.List;
import java.util.Set;

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
	
	public List<Post> getPostList(Pageable pageable, Set<String> targets, String keyword){
		boolean containTitle = targets.contains("title");
		boolean containContent = targets.contains("content");
		List<Post> postList;
		if(containTitle && containContent) {
			postList = this.postRepository.findAllByTitleContainingOrContentContaining(pageable, keyword, keyword).getContent();
		}
		else if(containTitle) {
			postList = this.postRepository.findAllByTitleContaining(pageable, keyword).getContent();
		}
		else if(containTitle) {
			postList = this.postRepository.findAllByContentContaining(pageable, keyword).getContent();
		}
		else {
			postList = this.postRepository.findAll(pageable).getContent();
		}
		
		return postList;
	}
	
	public Post getPost(long postId) {
		return this.postRepository.findById(postId).orElseThrow();
	}
	
	public void deletePost(long postId, String password) {
		Post saved = this.postRepository.findById(postId).orElseThrow();
		if(!saved.getPassword().equals(password)) {
			throw new RuntimeException("wrong password");
		}
		this.postRepository.delete(saved);
	}
	
	public void modifyPost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow();
		if(!saved.getPassword().equals(post.getPassword())) {
			throw new RuntimeException("wrong password");
		}
		saved.setTitle(post.getTitle());
		saved.setContent(post.getContent());
		this.postRepository.save(saved);
	}
}
