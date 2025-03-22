package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private String password;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private User user;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private Image image;
	@Column
	private Integer view = 0;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private List<Comment> commentList;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private List<PostLike> postLikeList;
	@Formula("(SELECT COUNT(*) FROM comment c WHERE c.post_id = id)")
	private Integer comments;
	@Formula("(SELECT COUNT(*) FROM post_like l WHERE l.post_id = id)")
	private Integer postLikes;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;	
	public Post(Integer id, String title, String content, String password, User user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.password = password;
		this.user = user;
	}
	public Post() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getPassword() {
		return password;
	}
	public User getUser() {
		return user;
	}
	public Image getImage() {
		return image;
	}
	public Integer getView() {
		return view;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public List<PostLike> getPostLikeList() {
		return postLikeList;
	}
	public Integer getComments() {
		return comments;
	}
	public Integer getPostLikes() {
		return postLikes;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void increaseView() {
		this.view++;
	}
	public void updateTitle(String title) {
		this.title = title;
	}
	public void updateContent(String content) {
		this.content = content;
	}
	public void addComment(Comment comment) {
		this.commentList.add(comment);
	}
	public void addPostLike(PostLike postLike) {
		this.postLikeList.add(postLike);
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public boolean isAnonymousPost() {
		return this.user == null;
	}
	public boolean verifyPassword(String password) {
		return this.password.equals(password);
	}
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder{
		private Integer id;
		private String title;
		private String content;
		private String password;
		private User user;
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		public Builder user(User user) {
			this.user = user;
			return this;
		}
		public Post build() {
			return new Post(id, title, content, password, user);
		}
	}
	
}
