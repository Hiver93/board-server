package com.example.demo.facade;

import java.io.IOException;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.CommentReqDto;
import com.example.demo.dto.PostReqDto;
import com.example.demo.dto.PostResDto.Detail;
import com.example.demo.dto.PostResDto.PostList;
import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;
import com.example.demo.service.ImageService;
import com.example.demo.service.PostLikeService;
import com.example.demo.service.PostService;

@Service
public class PostFacade {

	private PostService postService;
	private PostLikeService postLikeService;
	private CommentService commentService;
	private ImageService imageService;
	private AuthService authService;		
	public PostFacade(PostService postService, PostLikeService postLikeService, CommentService commentService,
			ImageService imageService, AuthService authService) {
		super();
		this.postService = postService;
		this.postLikeService = postLikeService;
		this.commentService = commentService;
		this.imageService = imageService;
		this.authService = authService;
	}


	@Transactional
	public void createPost(PostReqDto.Create dto, MultipartFile imageFile) throws IOException {
		User user = this.authService.getAuthentication();
		Post post;
		if(user == null) {
			post = this.postService.createPost(dto.getTitle(), dto.getContent(), dto.getPassword());
		}
		else {
			post = this.postService.createPost(dto.getTitle(), dto.getContent(), this.authService.getAuthentication());
		}
		
		if(imageFile != null) {
			this.imageService.saveImage(imageFile, post);
		}
	}
	
	public PostList getPostList(Pageable pageable, String keyword, Set<String> target) {
		return PostList.from(this.postService.getTotalSize(keyword, target), this.postService.getPostList(pageable, keyword, target));
	}
	
	public Detail readPost(Integer postId) {
		Detail detail = Detail.from(this.postService.getPost(postId));
		this.postService.increaseView(postId);
		return detail;
	}
	
	@Transactional
	public void removePost(Integer postId, PostReqDto.Delete dto) {
		Post saved = this.postService.getPost(postId);
		if(saved.isAnonymousPost()) {
			this.postService.removePost(postId, dto.getPassword());
		}
		else {
			this.authService.authenticate();
			this.postService.removePost(postId, this.authService.getAuthentication());
		}		
	}
	
	@Transactional
	public void modifyPost(Integer postId, PostReqDto.Put dto) {
		Post saved = this.postService.getPost(postId);
		if(saved.isAnonymousPost()) {
			this.postService.modifyPost(postId, dto.getTitle(), dto.getContent(), dto.getPassword());
		}
		else {
			this.authService.authenticate();
			this.postService.modifyPost(postId, dto.getTitle(), dto.getContent(), this.authService.getAuthentication());
		}
	}
	
	@Transactional
	public void addComment(Integer postId, CommentReqDto.Create dto) {
		this.authService.authenticate();
		Post saved = this.postService.getPost(postId);
		this.commentService.createComment(saved, dto.getContent(), this.authService.getAuthentication());
	}
	
	public void removeComment(Integer commentId) {
		this.authService.authenticate();
		this.commentService.removeComment(commentId, this.authService.getAuthentication());
	}
	
	@Transactional
	public void addPostLike(Integer postId) {
		this.authService.authenticate();
		Post saved = this.postService.getPost(postId);
		this.postLikeService.createPostLike(saved, this.authService.getAuthentication());
	}
	
	public void removePostLike(Integer postId) {
		this.authService.authenticate();
		this.postLikeService.removePostLike(postId, this.authService.getAuthentication());
	}
}
