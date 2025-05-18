package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Getter
@NoArgsConstructor
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
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	@Column
	private Integer view = 0;
	@Getter(value = AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private List<Image> imageList = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private List<Comment> commentList = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private List<PostLike> postLikeList = new ArrayList<>();
	@Formula("(SELECT COUNT(*) FROM comment c WHERE c.post_id = id)")
	private Integer comments;
	@Formula("(SELECT COUNT(*) FROM post_like l WHERE l.post_id = id)")
	private Integer postLikes;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;
	@Builder
	public Post(Integer id, String title, String content, String password, User user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.password = password;
		this.user = user;
	}
	
	public Image getImage() {
		if(this.imageList.isEmpty()) {
			return null;
		}
		return this.imageList.get(0);
	}
	
	public void updateTitle(String title) {
		this.title = title;
	}
	public void updateContent(String content) {
		this.content = content;
	}
	public void setImage(Image image) {
		if(imageList.isEmpty()) {
			imageList.add(image);
		}
		else {
			imageList.set(0, image);
		}
	}
	
	public void addComment(Comment comment) {
		this.commentList.add(comment);
	}
	public void addPostLike(PostLike postLike) {
		this.postLikeList.add(postLike);
	}
	
	public boolean isAnonymousPost() {
		return this.user == null;
	}
	public boolean verifyPassword(String password) {
		return this.password.equals(password);
	}
	
	public void increaseView() {
		this.view++;
	}
	
}
