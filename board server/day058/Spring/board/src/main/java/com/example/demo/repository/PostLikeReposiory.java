package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.PostLike;

@Repository
public interface PostLikeReposiory extends JpaRepository<PostLike, Integer>{
	
	public boolean existsByUserIdAndPostId(Integer userId, Integer postId);
}
