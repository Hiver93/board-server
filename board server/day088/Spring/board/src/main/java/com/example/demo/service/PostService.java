package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.PostRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class PostService {

	private PostRepository postRepository;
	private EntityManager entityManager;
	public PostService(PostRepository postRepository, EntityManager entityManager) {
		super();
		this.postRepository = postRepository;
		this.entityManager = entityManager;
	}
	
	public Post createPost(String title, String content, String password) {
		if(password.isBlank()) {
			throw new BoardException(ErrorCode.PASSWORD_REQUIRED);
		}
		return this.postRepository.save(Post.builder()
				.title(title)
				.content(content)
				.password(password)
				.build());
	}
	
	public Post createPost(String title, String content, User user) {
		return this.postRepository.save(Post.builder()
				.title(title)
				.content(content)
				.user(user)
				.build());
	}
	
	public Page<Post> getPage(Pageable pageable, String keyword, Set<String> target){
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Post> cq = cb.createQuery(Post.class);
		Root<Post> root = cq.from(Post.class);
		root.fetch("user",JoinType.LEFT);
		
		cq.select(root);
		
		if(0 < target.size()) {
			cq.where(
					cb.or(target.stream().map(str->{
						if(str.equals("nickname")) {
							return cb.like(root.get("user").get("nickname"), "%" + keyword + "%");
						}
						else {
							return cb.like(root.get(str), "%" + keyword + "%");
						}
					}).toArray(Predicate[]::new))
					);
		}
		
		List<Order> orderList = new ArrayList<>();
		pageable.getSort().forEach(order->{
			if(order.isAscending()) {
				orderList.add(cb.asc(root.get(order.getProperty())));
			}
			else {
				orderList.add(cb.desc(root.get(order.getProperty())));
			}
		});
		
		if(!orderList.isEmpty()) {
			cq.orderBy(orderList);
		}
		
		TypedQuery<Post> tq = entityManager.createQuery(cq);
		tq.setFirstResult((int)pageable.getOffset());
		tq.setMaxResults(pageable.getPageSize());
		List<Post> pList = tq.getResultList();
		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Post> countRoot = countQuery.from(Post.class);
		countQuery.select(cb.count(countRoot));
		Long totalSize = entityManager.createQuery(countQuery).getSingleResult();
		
		return new PageImpl<>(pList, pageable, totalSize);		
	}
	
	public Post getPost(Integer postId) {
		return this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
	}
	
	@Async
	@Transactional
	public void increaseView(Integer postId) {
		Post saved = this.postRepository.findWithLockById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		saved.increaseView();
	}
	
	@Transactional
	public void removePost(Integer postId, String password) {
		if(password.isBlank()) {
			throw new BoardException(ErrorCode.PASSWORD_REQUIRED);
		}
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.verifyPassword(password)) {
			throw new BoardException(ErrorCode.INCORRECT_PASSWORD);
		}
		this.postRepository.delete(saved);
	}
	
	@Transactional
	public void removePost(Integer postId, User user) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		this.postRepository.delete(saved);
	}
	
	@Transactional
	public void modifyPost(Integer postId, String title, String content, String password) {
		if(password.isBlank()) {
			throw new BoardException(ErrorCode.PASSWORD_REQUIRED);
		}
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.verifyPassword(password)) {
			throw new BoardException(ErrorCode.INCORRECT_PASSWORD);
		}
		saved.updateTitle(title);
		saved.updateContent(content);
	}
	
	@Transactional
	public void modifyPost(Integer postId, String title, String content, User user) {
		Post saved = this.postRepository.findById(postId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		saved.updateTitle(title);
		saved.updateContent(content);
	}
}
