package com.example.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.board.domain.Post;

@Repository
public class PostRepository {
	private Map<Long,Post> postMap = new HashMap<>();
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
	
	public Post findById(long postId) {
		return postMap.get(postId);
	}
	
	public void deleteById(long postId) {
		if(!postMap.containsKey(postId)) {
			throw new RuntimeException("not found");
		}
		postMap.remove(postId);
	}
	
	public void update(Post post) {
		if(!postMap.containsKey(post.getId())) {
			throw new RuntimeException("not found");
		}
		postMap.put(post.getId(), post);
	}
}
