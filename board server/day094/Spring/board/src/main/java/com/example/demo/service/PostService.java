package com.example.demo.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;

public interface PostService {

	public Post createPost(String title, String content, String password);
	public Post createPost(String title, String content, User user);
	public Page<Post> getPage(Pageable pageable, String keyword, Set<String> target);
	public Post getPost(Integer postId);
	public void increaseView(Integer postId);
	public void removePost(Integer postId, String password);
	public void removePost(Integer postId, User user);
	public void modifyPost(Integer postId, String title, String content, String password);
	public void modifyPost(Integer postId, String title, String content, User user);
}
