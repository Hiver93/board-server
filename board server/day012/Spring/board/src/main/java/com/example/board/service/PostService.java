package com.example.board.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.board.dto.PostResponseDto;
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
	
	public List<PostResponseDto> getPostList(Pageable pageable, String keyword, Set<String> targets){
		List<Post> result;
		boolean containTitle = targets.contains("title");
		boolean containContent = targets.contains("content");
		if(containTitle && containContent) {
			result = this.postRepository.findAllByTitleContainingOrContentContaining(pageable, keyword, keyword).getContent();
		}
		else if(containTitle) {
			result = this.postRepository.findAllByTitleContaining(pageable, keyword).getContent();
		}
		else if(containContent) {
			result = this.postRepository.findAllByContentContaining(pageable, keyword).getContent();
		}
		else {
			result = this.postRepository.findAll(pageable).getContent();
		}
		
		return result.stream()
				.map(post -> new PostResponseDto(post.getId(), post.getTitle(), post.getContent(),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 
						post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
				.collect(Collectors.toList());		
	}
	
	public PostResponseDto getPost(long postId) {
		Post post = postRepository.findById(postId).orElseThrow(); 
		return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(),
				post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 
				post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
