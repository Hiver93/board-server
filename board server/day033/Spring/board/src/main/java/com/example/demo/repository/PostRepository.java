package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	Page<Post> findAllByTitleContainingOrContentContaining(Pageable pageable, String title, String content);
	Page<Post> findAllByTitleContaining(Pageable pageable, String title);
	Page<Post> findAllByContentContaining(Pageable pageable, String content);
	
	long countByTitleContainingOrContentContaining(String title, String content);
	long countByTitleContaining(String title);
	long countByContentContaining(String content);
		
}
