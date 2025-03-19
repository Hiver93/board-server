package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

import jakarta.persistence.LockModeType;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	@Query(value = "SELECT p FROM Post p LEFT JOIN FETCH p.user WHERE p.title LIKE %:title% OR p.content LIKE %:content%",
			countQuery = "SELECT COUNT(*) from Post p WHERE p.title LIKE %:title% OR p.content LIKE %:content%")
	public Page<Post> findAllByTitleContainingOrContentContaining(Pageable pageable, @Param("title") String title, @Param("content") String content);
	@Query(value = "SELECT p FROM Post p LEFT JOIN FETCH p.user WHERE p.title LIKE %:title%",
			countQuery = "SELECT COUNT(*) from Post p WHERE p.title LIKE %:title%")
	public Page<Post> findAllByTitleContaining(Pageable pageable, @Param("title") String title);
	@Query(value = "SELECT p FROM Post p LEFT JOIN FETCH p.user WHERE p.content LIKE %:content%",
			countQuery = "SELECT COUNT(*) from Post p WHERE p.content LIKE %:content%")
	public Page<Post> findAllByContentContaining(Pageable pageable, @Param("content") String content);
	@Query(value = "SELECT p FROM Post p JOIN FETCH p.user WHERE p.user.nickname LIKE %:nickname%",
			countQuery = "SELECT COUNT(*) from Post p WHERE p.user.nickname LIKE %:nickname%")
	public Page<Post> findByUser_NicknameContaining(Pageable pageable, @Param("nickname") String nickname);
	@Query(value = "SELECT p FROM Post p LEFT JOIN FETCH p.user",
			countQuery = "SELECT COUNT(*) from Post p")
	public Page<Post> findAll(Pageable pageable);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Optional<Post> findWithLockById(Integer id);
	
}
