package com.example.demo.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.board.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	public Page<Post> findAllByTitleContaining(String keyword, Pageable pageable);
}
