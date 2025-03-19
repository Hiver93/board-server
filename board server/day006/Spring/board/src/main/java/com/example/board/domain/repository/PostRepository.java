package com.example.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
