package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

	public Optional<Comment> findByIdAndPostId(Integer id, Integer postId);
}
