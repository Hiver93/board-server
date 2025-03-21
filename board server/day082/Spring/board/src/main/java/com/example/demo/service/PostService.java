package com.example.demo.service;

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
	
	public Post createPost(String title, String content, String password) {
		if(password == null) {
			throw new BoardException(ErrorCode.PASSWORD_REQUIRED);
		}
		return this.postRepository.save(
				Post.builder()
				.title(title)
				.content(content)
				.password(password)
				.build());
	}
	
	public Post createPost(String title, String content, User user) {		
		return this.postRepository.save(
				Post.builder()
				.title(title)
				.content(content)
				.user(user)
				.build());
	}
	
	public Page<Post> getPage(Pageable pageable, String keyword, Set<String> target){
		boolean title = target.contains("title");
		boolean content = target.contains("content");
		boolean nickname = target.contains("nickname");
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
		else if(nickname) {
			page = this.postRepository.findAllByUser_NicknameContaining(pageable, keyword);
		}
		else {
			page = this.postRepository.findAll(pageable);
		}
		return page;
	}
	
	public Post getPost(Integer postId) {
		return this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
	}
	
	@Async
	@Transactional
	public void increaseView(Integer postId) {
		Post saved = this.postRepository.findWithLockById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		saved.increaseView();
	}
	
	public void removePost(Integer postId, String password) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.verifyPassword(password)) {
			throw new BoardException(ErrorCode.INCORRECT_PASSWORD);
		}
		this.postRepository.delete(saved);
	}
	
	public void removePost(Integer postId, User user) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		this.postRepository.delete(saved);
	}
	
	@Transactional
	public void modifyPost(Integer postId, String title, String content, String password) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.verifyPassword(password)) {
			throw new BoardException(ErrorCode.INCORRECT_PASSWORD);
		}
		saved.updateTitle(title);
		saved.updateContent(content);
	}
	
	@Transactional
	public void modifyPost(Integer postId, String title, String content, User user) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		saved.updateTitle(title);
		saved.updateContent(content);
	}
}
