package com.example.demo.repository.criteria;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryCriteriaImpl implements PostRepositoryCriteria{

	private final EntityManager entityManager;
	
	@Override
	public Page<Post> findAllByKeyword(Pageable pageable, String keyword, Set<String> target) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Post> cq = cb.createQuery(Post.class);
		Root<Post> root = cq.from(Post.class);
		
		root.fetch("user",JoinType.LEFT);
		cq.select(root);
		
		Predicate[] predicates = target.stream().map(str -> {
			if("nickname".equals(str)) {
				return cb.like(root.get("user").get("nickname"), "%" + keyword + "%");
			}
			else {
				return cb.like(root.get(str), "%" + keyword + "%");
			}
		}).toArray(Predicate[]::new);
		
		if(0 < predicates.length) {
			cq.where(predicates);
		}
		
		List<Order> orderList = pageable.getSort().stream().map(order -> {
			if(order.isAscending()) {
				return cb.asc(root.get(order.getProperty()));
			}
			else {
				return cb.desc(root.get(order.getProperty()));
			}
		}).toList();
		
		List<Post> postList = entityManager
				.createQuery(cq)
				.setFirstResult((int)pageable.getOffset())
				.setMaxResults(pageable.getPageSize())
				.getResultList();
		
		if(!postList.isEmpty()) {
			cq.orderBy(orderList);
		}
		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Post>countRoot = countQuery.from(Post.class);
		
		predicates = target.stream().map(str -> {
			if("nickname".equals(str)) {
				return cb.like(countRoot.get("user").get("nickname"), "%" + keyword + "%");
			}
			else {
				return cb.like(countRoot.get(str), "%" + keyword + "%");
			}
		}).toArray(Predicate[]::new);
		
		if(0 < predicates.length) {
			countQuery.where(predicates);
		}
		
		Long totalCount = entityManager.createQuery(countQuery).getSingleResult();
		
		return new PageImpl<>(postList, pageable, totalCount);
	}

}
