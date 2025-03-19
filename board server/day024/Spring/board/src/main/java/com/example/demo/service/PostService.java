package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.error.ErrorCode;
import com.example.demo.error.PostException;
import com.example.demo.repository.PostMapper;

@Service
public class PostService {

	public PostMapper postMapper;

	public PostService(PostMapper postMapper) {
		super();
		this.postMapper = postMapper;
	}
	
	public void createPost(Post post) {
		this.postMapper.save(post);
	}
	
	public List<Post> getPostList(Pageable pageable, String keyword, Set<String> targets){
		boolean containTitle = targets.contains("title");
		boolean containContent = targets.contains("content");
		List<Post> postList;
		if(containTitle && containContent) {
			postList = this.postMapper.findAllByTitleOrContent(pageable, keyword);
		}
		else if(containTitle) {
			postList = this.postMapper.findAllByTitle(pageable, keyword);
		}
		else if(containContent) {
			postList = this.postMapper.findAllByContent(pageable, keyword);
		}
		else {
			postList = this.postMapper.findAll(pageable);
		}
		return postList;
	}
	
	public Post getPost(long postId) {
		return this.postMapper.findById(postId).orElseThrow(()->{throw new PostException(ErrorCode.POST_NOT_FOUND);});
	}
	
	public long getTotalSize(String keyword, Set<String> targets) {
		boolean containTitle = targets.contains("title");
		boolean containContent = targets.contains("content");
		long size;
		if(containTitle && containContent) {
			size = this.postMapper.countByTitleOrContent(keyword);
		}
		else if(containTitle) {
			size = this.postMapper.countByTitle(keyword);
		}
		else if(containContent) {
			size = this.postMapper.countByContent(keyword);
		}
		else {
			size = this.postMapper.count();
		}
		return size;
	}
}
