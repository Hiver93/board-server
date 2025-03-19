package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}
	
	public void createPost(Post post) {
		this.postRepository.save(post);
	}
	
	public List<Post> getPostList(Pageable pageable, String keyword, Set<String> targets){
		boolean conTitle = targets.contains("title");
		boolean conContent = targets.contains("content");
		Page<Post> page;
		if(conTitle&&conContent) {
			page = this.postRepository.findAllByTitleContainingOrContentContaining(pageable, keyword, keyword);
		}
		else if(conTitle) {
			page = this.postRepository.findAllByTitleContaining(pageable, keyword);
		}
		else if(conContent) {
			page = this.postRepository.findAllByContentContaining(pageable, keyword);
		}
		else {
			page = this.postRepository.findAll(pageable);
		}
		return page.getContent();
	}
	
	public long getTotalSize(String keyword, Set<String> targets){
		boolean conTitle = targets.contains("title");
		boolean conContent = targets.contains("content");
		long size;
		if(conTitle&&conContent) {
			size = this.postRepository.countByTitleContainingOrContentContaining(keyword, keyword);
		}
		else if(conTitle) {
			size = this.postRepository.countByTitleContaining(keyword);
		}
		else if(conContent) {
			size = this.postRepository.countByContentContaining(keyword);
		}
		else {
			size = this.postRepository.count();
		}
		return size;
	}
	
	public Post readPost(Integer postId) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.POST_NOT_FOUND);});
		saved.increaseView();
		this.postRepository.save(saved);
		return saved;
	}
	
	public void removePost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow(()->{throw new BoardException(ErrorCode.POST_NOT_FOUND);});
		if(!saved.getUser().getId().equals(post.getUser().getId())) {
			throw new BoardException(ErrorCode.NOT_POST_OWNER);
		}
		this.postRepository.delete(saved);
	}
	
	public void modifyPost(Post post) {
		Post saved = this.postRepository.findById(post.getId()).orElseThrow(()->{throw new BoardException(ErrorCode.POST_NOT_FOUND);});
		if(!saved.getUser().getId().equals(post.getUser().getId())) {
			throw new BoardException(ErrorCode.NOT_POST_OWNER);
		}
		saved.updateTitle(post.getTitle());
		saved.updateContent(post.getContent());
		this.postRepository.save(saved);
	}
}
