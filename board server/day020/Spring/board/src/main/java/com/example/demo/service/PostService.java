package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.error.PostException;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		super();
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
			postList = this.postRepository.findAllByTitleContainingOrContentContaining(pageable, keyword, keyword).getContent();
		}
		else if(containTitle) {
			postList = this.postRepository.findAllByTitleContaining(pageable, keyword).getContent();
		}
		else if(containContent) {
			postList = this.postRepository.findAllByContentContaining(pageable, keyword).getContent();
		}
		else {
			postList = this.postRepository.findAll(pageable).getContent();
		}
		return postList;
	}
	
	public Post getPost(long postId) {
		return this.postRepository.findById(postId).orElseThrow(()->{throw new PostException(HttpStatus.NOT_FOUND,"post not found");});
	}
	
	public void deletePost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow(()->{throw new PostException(HttpStatus.NOT_FOUND,"post not found");});
		if(!saved.getPassword().equals(post.getPassword())) {
			throw new PostException(HttpStatus.UNAUTHORIZED, "incorrect password");
		}
		this.postRepository.delete(saved);
	}
	
	public void modifyPost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow(()->{throw new PostException(HttpStatus.NOT_FOUND,"post not found");});
		if(!saved.getPassword().equals(post.getPassword())) {
			throw new PostException(HttpStatus.UNAUTHORIZED, "incorrect password");
		}
		saved.setTitle(post.getTitle());
		saved.setContent(post.getContent());
		this.postRepository.save(saved);
	}
	
	public long getTotalSize(String keyword, Set<String> targets) {
		boolean containTitle = targets.contains("title");
		boolean containContent = targets.contains("content");
		
		long size;
		if(containTitle && containContent) {
			size = this.postRepository.countByTitleContainingOrContentContaining(keyword, keyword);
		}
		else if(containTitle) {
			size = this.postRepository.countByTitleContaining(keyword);
		}
		else if(containContent) {
			size = this.postRepository.countByContentContaining(keyword);
		}
		else {
			size = this.postRepository.count();
		}
		return size;
	}
}
