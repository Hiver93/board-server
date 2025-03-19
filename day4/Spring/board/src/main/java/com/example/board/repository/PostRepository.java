package com.example.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.board.entity.Post;

@Repository
public class PostRepository {
	private Map<Long, Post> postMap = new HashMap<>();
	private long lastId = 0;
	
	public void save(Post post) {
		post.setId(++lastId);
		postMap.put(post.getId(), post);
	}
	
	public List<Post> findAll(){
		List<Post> postList = new ArrayList<>();
		for(var entry : postMap.entrySet()){
			postList.add(entry.getValue());
		}
		return postList;
	}
	
	public Post findById(long id) {
		return postMap.get(id);
	}
	
	public void deleteById(long id) {
		postMap.remove(id);
	}
	
	public Post updatePost(Post post) {
		if(!postMap.containsKey(post.getId())) {
			throw new RuntimeException();
		}
		postMap.put(post.getId(), post);
		System.out.println(postMap.get(post.getId()));
		return post;
		
	}
}
