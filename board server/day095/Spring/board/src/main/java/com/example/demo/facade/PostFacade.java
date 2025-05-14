package com.example.demo.facade;

import java.io.IOException;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.domain.Post;
import com.example.demo.dto.request.CommentReqDto;
import com.example.demo.dto.request.PostReqDto;
import com.example.demo.dto.response.PostResDto.Detail;
import com.example.demo.dto.response.PostResDto.PostList;
import com.example.demo.service.AuthService;
import com.example.demo.service.BadwordService;
import com.example.demo.service.CommentService;
import com.example.demo.service.FileService;
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
	private final FileService fileService;
	private final BadwordService badwordService;
	private final AuthService authService;
	
	@Transactional
	public void writePost(PostReqDto.Create dto, MultipartFile file) {
		Post post;
		String title = this.badwordService.mask(dto.getTitle());
		String content = this.badwordService.mask(dto.getContent());
		if(this.authService.isAuthenticated()) {
			post = this.postService.createPost(title, content, dto.getPassword());
		}
		else {
			post = this.postService.createPost(title, content, this.authService.authenticate());
		}
		
		if(file != null) {
			Image image = this.imageService.createImage(post, file);
			post.setImage(image);
			try {
				this.fileService.saveFile(content, file.getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public Detail readPost(Integer postId) {
		Post post = this.postService.getPost(postId);
		this.postService.increaseView(postId);
		return Detail.from(post);
	}
	
	public PostList getPostList(Pageable pageable, String keyword, Set<String> target) {
		return PostList.from(this.postService.getPage(pageable, keyword, target));
	}
	
	@Transactional
	public void removePost(Integer postId, PostReqDto.Delete dto) {
		Post post = this.postService.getPost(postId);
		String fileName = post.getImage() == null ? null : post.getImage().getFileName();
		if(post.isAnonymousPost()) {
			this.postService.removePost(postId, dto.getPassword());
		}
		else {
			this.postService.removePost(postId, this.authService.authenticate());
		}
		
		if(fileName != null) {
			this.fileService.deleteFile(fileName);
		}
	}
	
	@Transactional
	public void modifyPost(Integer postId, PostReqDto.Put dto, MultipartFile file) {
		Post post = this.postService.getPost(postId);
		
		String title = this.badwordService.mask(dto.getTitle());
		String content = this.badwordService.mask(dto.getContent());
		
		if(post.isAnonymousPost()) {
			this.postService.modifyPost(postId, title, content, dto.getPassword());
		}
		else {
			this.postService.modifyPost(postId, title, content, this.authService.authenticate());
		}

		if(file != null) {
			String fileName = post.getImage() == null ? null : post.getImage().getFileName();
			Image image = this.imageService.createImage(post, file);
			try {
				this.fileService.saveFile(image.getFileName(), file.getBytes());
				if(fileName != null) {
					this.fileService.deleteFile(fileName);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Transactional
	public void addComment(Integer postId, CommentReqDto.Create dto) {
		Post post = this.postService.getPost(postId);
		String content = this.badwordService.mask(dto.getContent());
		post.addComment(this.commentService.createComment(post, content, this.authService.authenticate()));
	}
	
	public void removeComment(Integer commentId) {
		this.commentService.removeComment(commentId, this.authService.authenticate());
	}
	
	public void modifyComment(Integer commentId, CommentReqDto.Put dto) {
		String content = this.badwordService.mask(dto.getContent());
		this.commentService.modifyComment(commentId, content, this.authService.authenticate());
	}
	
	@Transactional
	public void addPostLike(Integer postId) {
		Post post = this.postService.getPost(postId);
		post.addPostLike(this.postLikeService.createPostLike(post, this.authService.authenticate()));
	}
	
	public void removePostLike(Integer postId) {
		this.postLikeService.removePostLike(postId, this.authService.authenticate());
	}
	
}
