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
	
	public List<Post> getPostList(Pageable pageable, String keyword, Set<String> targets){
		boolean containTitle = targets.contains("title");
		boolean containContent = targets.contains("content");
		List<Post> postList;
		if(containTitle && containContent) {
			return this.postRepository.findAllByTitleContainingOrContentContaining(pageable, keyword, keyword).getContent();
		}
		if(containTitle) {
			return this.postRepository.findAllByTitleContaining(pageable, keyword).getContent();
		}
		if(containContent) {
			return this.postRepository.findAllByContentContaining(pageable, keyword).getContent();
		}
		return this.postRepository.findAll(pageable).getContent();
	}
	
	public Post getPost(long postId) {
		Post saved = this.postRepository.findById(postId).orElseThrow(); 
		saved.setView(saved.getView()+1);
		this.postRepository.save(saved);
		return saved;
	}
	
	public void deletePost(long postId, String password) {
		Post saved = this.postRepository.findById(postId).orElseThrow();
		if(!saved.getPassword().equals(password)) {
			throw new RuntimeException("wrong pw");
		}
		this.postRepository.delete(saved);
	}
	
	public void modifyPost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow();
		if(!saved.getPassword().equals(post.getPassword())) {
			throw new RuntimeException("wrong pw");
		}
		saved.setTitle(post.getTitle());
		saved.setContent(post.getContent());
		this.postRepository.save(saved);
	}
	
	public long getTotalSize(String keyword, Set<String> targets) {
		boolean containTitle = targets.contains("title");
		boolean containContent = targets.contains("content");
		
		if(containTitle && containContent) {
			return this.postRepository.countByTitleContainingOrContentContaining(keyword, keyword);
		}
		if(containTitle) {
			return this.postRepository.countByTitleContaining(keyword);
		}
		if(containContent) {
			return this.postRepository.countByContentContaining(keyword);
		}
		return this.postRepository.count();
	}
}
