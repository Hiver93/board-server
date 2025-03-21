package com.example.demo.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String content;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private User user;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Post post;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;
	public Comment(Integer id, String content, User user, Post post) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.post = post;
	}
	public Comment() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public User getUser() {
		return user;
	}
	public Post getPost() {
		return post;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder{
		private Integer id;
		private String content;
		private User user;
		private Post post;
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		public Builder user(User user) {
			this.user = user;
			return this;
		}
		public Builder post(Post post) {
			this.post = post;
			return this;
		}
		public Comment build() {
			return new Comment(id, content, user, post);
		}
	}
}
