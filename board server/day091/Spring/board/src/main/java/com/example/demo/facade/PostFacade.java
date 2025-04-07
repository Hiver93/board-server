package com.example.demo.facade;

import java.io.IOException;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Post;
import com.example.demo.dto.CommentReqDto;
import com.example.demo.dto.PostReqDto;
import com.example.demo.dto.PostResDto.Detail;
import com.example.demo.dto.PostResDto.PostList;
import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;
import com.example.demo.service.ImageService;
import com.example.demo.service.PostLikeService;
import com.example.demo.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostFacade {

	private final PostService postService;
	private final CommentService commentService;
	private final PostLikeService postLikeService;
	private final ImageService imageService;
	private final AuthService authService;
	
	public void writePost(PostReqDto.Create dto, MultipartFile file) throws IOException {
		Post post;
		if(this.authService.isAuthenticated()) {
			post = this.postService.createPost(dto.getTitle(), dto.getContent(), this.authService.getAuthentication());
		}
		else {
			post = this.postService.createPost(dto.getTitle(), dto.getContent(), dto.getPassword());
		}
		
		if(file != null) {
			post.setImage(this.imageService.saveImage(post, file));
		}
	}
	
	public PostList getPostList(Pageable pageable, String keyword, Set<String> target) {
		return PostList.from(this.postService.getPage(pageable, keyword, target));
	}
	
	public Detail readPost(Integer postId) {
		Post post = this.postService.getPost(postId);
		this.postService.increaseView(postId);
		return Detail.from(post);
	}
	
	@Transactional
	public void removePost(Integer postId, PostReqDto.Delete dto) {
		Post post = this.postService.getPost(postId);
		if(post.isAnonymousPost()) {
			this.postService.removePost(postId, dto.getPassword());
		}
		else {
			this.postService.removePost(postId, this.authService.getAuthentication());
		}
	}
	
	@Transactional
	public void modifyPost(Integer postId, PostReqDto.Put dto, MultipartFile file) throws IOException {
		Post post = this.postService.getPost(postId);
		if(post.isAnonymousPost()) {
			this.postService.modifyPost(postId, dto.getTitle(), dto.getContent(), dto.getContent());
		}
		else {
			this.postService.modifyPost(postId, dto.getTitle(), dto.getContent(), this.authService.getAuthentication());
		}
		
		if(file != null) {
			post.setImage(this.imageService.saveImage(post, file));
		}
	}
	
	@Transactional
	public void addComments(Integer postId, CommentReqDto.Create dto) {
		Post post = this.postService.getPost(postId);
		post.addComment(this.commentService.createComment(post, dto.getContent(), this.authService.getAuthentication()));
	}
	
	public void removeComment(Integer commentId) {
		this.commentService.removeComment(commentId, this.authService.getAuthentication());
	}
	
	public void modifyComment(Integer commentId, CommentReqDto.Put dto) {
		this.commentService.modifyComment(commentId, dto.getContent(), this.authService.getAuthentication());
	}
	
	@Transactional
	public void addPostLike(Integer postId) {
		Post post = this.postService.getPost(postId);
		post.addPostLike(this.postLikeService.createPostLike(post, this.authService.getAuthentication()));
	}
	
	public void removePostLike(Integer postId) {
		this.postLikeService.removePostLike(postId, this.authService.getAuthentication());
	}
}
