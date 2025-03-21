package com.example.demo.facade;

import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.CommentReqDto;
import com.example.demo.dto.PostReqDto;
import com.example.demo.dto.PostResDto;
import com.example.demo.dto.PostResDto.PostList;
import com.example.demo.service.AuthService;
import com.example.demo.service.PostService;

@Component
public class PostFacade {

	private PostService postService;
	private AuthService authService;
	
	public PostFacade(PostService postService, AuthService authService) {
		super();
		this.postService = postService;
		this.authService = authService;
	}

	public void createPost(PostReqDto.Create dto) {
		User user = this.authService.getAuthentication();
		if(user == null) {
			this.postService.createPost(dto.getTitle(), dto.getContent(), dto.getPassword());			
		}
		else {
			this.postService.createPost(dto.getTitle(), dto.getContent(), user);
		}		
	}
	
	public PostResDto.PostList getPostList(Pageable pageable, String keyword, Set<String> target){
		return PostList.from(this.postService.getTotalSize(keyword, target), this.postService.getPostList(pageable, keyword, target));
	}
	
	public PostResDto.Detail readPost(Integer postId){
		Post saved = this.postService.getPost(postId);
		this.postService.increaseView(postId);
		return PostResDto.Detail.from(saved);
	}
	
	@Transactional
	public void removePost(Integer postId, PostReqDto.Delete dto) {
		Post saved = this.postService.getPost(postId);
		if(saved.getUser() == null) {
			this.postService.removePost(postId, dto.getPassword());
		}
		else {
			this.authService.autheticate();
			this.postService.removePost(postId, this.authService.getAuthentication());
		}
	}
	
	@Transactional
	public void modifyPost(Integer postId, PostReqDto.Put dto) {
		Post saved = this.postService.getPost(postId);
		if(saved.getUser() == null) {
			this.postService.modifyPost(postId, dto.getTitle(), dto.getContent(), dto.getPassword());
		}
		else {
			this.authService.autheticate();
			this.postService.modifyPost(postId, dto.getTitle(), dto.getContent(), this.authService.getAuthentication());
		}
	}
	
	public void addComment(Integer postId, CommentReqDto.Create dto) {
		this.authService.autheticate();
		this.postService.addComment(postId, dto.getContent(), this.authService.getAuthentication());
	}
	
	public void addPostLike(Integer postId) {
		this.authService.autheticate();
		this.postService.addPostLike(postId, this.authService.getAuthentication());
	}
	
	public void removePostLike(Integer postId) {
		this.authService.autheticate();
		this.postService.removePostLike(postId, this.authService.getAuthentication());
	}
}
