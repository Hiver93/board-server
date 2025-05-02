package com.example.demo.repository.criteria;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
		CriteriaQuery<Post> postQuery = cb.createQuery(Post.class);
		Root<Post> root = postQuery.from(Post.class);
		
		root.fetch("user",JoinType.LEFT);
		postQuery.select(root);
		
		Predicate[] predicates = target.stream().map(str ->{
			if("nickname".equals(str)) {
				return cb.like(root.get("user").get(str), "%" + keyword + "%");
			}
			else {
				return cb.like(root.get(str), "%" + keyword + "%");
			}
		}).toArray(Predicate[]::new);
		
		if(0 < predicates.length) {
			postQuery.where(predicates);
		}
		
		List<Order> orderList = pageable.getSort().stream().map(order->{
			if(order.isAscending()) {
				return cb.asc(root.get(order.getProperty()));
			}
			else {
				return cb.desc(root.get(order.getProperty()));
			}
		}).toList();
		
		if(0 < orderList.size()) {
			postQuery.orderBy(orderList);
		}
		
		TypedQuery<Post> tq = entityManager.createQuery(postQuery);
		tq.setFirstResult((int)pageable.getOffset());
		tq.setMaxResults(pageable.getPageSize());
		List<Post> postList = tq.getResultList();
		
		CriteriaQuery<Long> sizeQuery = cb.createQuery(Long.class);
		Root<Post> sizeRoot = sizeQuery.from(Post.class);
		
		sizeQuery.select(cb.count(sizeRoot));
		
		predicates = target.stream().map(str ->{
			if("nickname".equals(str)) {
				return cb.like(sizeRoot.get("user").get(str), "%" + keyword + "%");
			}
			else {
				return cb.like(sizeRoot.get(str), "%" + keyword + "%");
			}
		}).toArray(Predicate[]::new);
		
		if(0 < predicates.length) {
			sizeQuery.where(predicates);
		}
		
		Long totalSize = entityManager.createQuery(sizeQuery).getSingleResult();
		return new PageImpl<Post>(postList, pageable, totalSize);
	}

	
}
