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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	
	public Post createPost(String title, String content, String password) {
		if(password.isBlank()) {
			throw new BoardException(ErrorCode.PASSWORD_REQUIRED);
		}
		
		return this.postRepository.save(Post.builder()
				.title(title)
				.content(content)
				.password(password)
				.build());
	}
	
	public Post createPost(String title, String content, User user) {
		
		return this.postRepository.save(Post.builder()
				.title(title)
				.content(content)
				.user(user)
				.build());
	}
	
	public void removePost(Integer postId, String password) {
		if(password.isBlank()) {
			throw new BoardException(ErrorCode.PASSWORD_REQUIRED);
		}
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
		if(password.isBlank()) {
			throw new BoardException(ErrorCode.PASSWORD_REQUIRED);
		}
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
	
	public Page<Post> getPage(Pageable pageable, String keyword, Set<String> target){
		return this.postRepository.findAllByKeyword(pageable, keyword, target);
	}
	
	public Post getPost(Integer postId) {
		return this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
	}
	
	@Transactional
	@Async
	public void increaseView(Integer postId) {
		Post saved = this.postRepository.findWithLockById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		saved.increaseView();
	}
}
