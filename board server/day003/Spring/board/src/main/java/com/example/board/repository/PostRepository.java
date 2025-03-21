package com.example.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.board.domain.Post;

@Repository
public class PostRepository {
	private Map<Long, Post> map = new HashMap<>();
	private long lastId = 0;
	
	public void save(Post post) {
		post.setId(++lastId);
		map.put(post.getId(), post);
	}
	
	public List<Post> findAll(){
		List<Post> postList = new ArrayList<>();
		for(var entry : map.entrySet()) {
			postList.add(entry.getValue());
		}
		return postList;
	}
	
	public Post findById(long id) {
		return map.get(id);
	}
	
	public void deleteById(long id) {
		map.remove(id);
	}
}
