package com.example.demo.service.impl;

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
import com.example.demo.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	
	private final PostRepository postRepository;
	
	@Override
	public Post createPost(String title, String content, String password) {

		return this.postRepository.save(Post.builder()
				.title(title)
				.content(content)
				.password(password)
				.build());
	}

	@Override
	public Post createPost(String title, String content, User user) {
		
		return this.postRepository.save(Post.builder()
				.title(title)
				.content(content)
				.user(user)
				.build());
	}

	@Override
	public Post getPost(Integer postId) {
		
		return this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
	}

	@Override
	@Transactional
	@Async
	public void increaseView(Integer postId) {
		Post saved = this.postRepository.findWithLockById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		saved.increaseView();
	}

	@Override
	public Page<Post> getPage(Pageable pageable, String keyword, Set<String> target) {
		
		return this.postRepository.findAllByKeyword(pageable, keyword, target);
	}

	@Override
	public void removePost(Integer postId, String password) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.verifyPassword(password)) {
			throw new BoardException(ErrorCode.INCORRECT_PASSWORD);
		}
		this.postRepository.delete(saved);
	}

	@Override
	public void removePost(Integer postId, User user) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		this.postRepository.delete(saved);
	}

	@Override
	@Transactional
	public void modifyPost(Integer postId, String title, String content, String password) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.verifyPassword(password)) {
			throw new BoardException(ErrorCode.INCORRECT_PASSWORD);
		}
		saved.updateTitle(title);
		saved.updateContent(content);
	}

	@Override
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
