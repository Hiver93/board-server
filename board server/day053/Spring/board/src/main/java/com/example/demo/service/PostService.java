package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
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
		boolean title = targets.contains("title");
		boolean content = targets.contains("content");
		Page<Post> page;
		if(title && content) {
			page = this.postRepository.findAllByTitleContainingOrContentContaining(pageable, keyword, keyword);
		}
		else if(title) {
			page = this.postRepository.findAllByTitleContaining(pageable, keyword);
		}
		else if(content) {
			page = this.postRepository.findAllByContentContaining(pageable, keyword);
		}
		else {
			page = this.postRepository.findAll(pageable);
		}
		return page.getContent();
	}
	
	public long getTotalSize(String keyword, Set<String> targets){
		boolean title = targets.contains("title");
		boolean content = targets.contains("content");
		long size;
		if(title && content) {
			size = this.postRepository.countByTitleContainingOrContentContaining(keyword, keyword);
		}
		else if(title) {
			size = this.postRepository.countByTitleContaining(keyword);
		}
		else if(content) {
			size = this.postRepository.countByContentContaining(keyword);
		}
		else {
			size = this.postRepository.count();
		}
		return size;
	}
	
	public Post getPost(Integer postId) {
		return this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.POST_NOT_FOUND);});
	}
	
	@Async
	@Transactional
	public void increaseView(Integer postId) {
		this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.POST_NOT_FOUND);})
		.increaseView();
	}
	
	public void deletePost(Integer postId, User user) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.POST_NOT_FOUND);});
		if(!saved.getUser().getId().equals(user.getId())) {
			throw new BoardException(ErrorCode.NOT_POST_OWNER);
		}
		this.postRepository.delete(saved);
	}
	
	@Transactional
	public void modifyPost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow(()->{throw new BoardException(ErrorCode.POST_NOT_FOUND);});
		if(!saved.getUser().getId().equals(post.getUser().getId())) {
			throw new BoardException(ErrorCode.NOT_POST_OWNER);
		}
		saved.updateTitle(post.getTitle());
		saved.updateContent(post.getContent());
	}
}
