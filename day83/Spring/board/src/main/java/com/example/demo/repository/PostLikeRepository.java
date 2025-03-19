package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.PostLike;
@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer>{

	public Optional<PostLike> findByUserIdAndPostId(Integer userId, Integer postId);
}
