package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
	public Comment createComment(Post post, String content, User user) {
		return this.commentRepository.save(Comment.builder()
				.post(post)
				.content(content)
				.user(user)
				.build()); 
	}
	
	public void removeComment(Integer commentId, User user) {
		Comment saved = this.commentRepository.findById(commentId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		this.commentRepository.delete(saved);
	}
	
	@Transactional
	public void modifyComment(Integer commentId, String content, User user) {
		Comment saved = this.commentRepository.findById(commentId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		saved.updateContent(content);
	}
}
