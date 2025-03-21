package com.example.demo.domain;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.util.FileUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostRemove;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String fileName;
	@Column
	private String originalFileName;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Post post;
	@CreatedDate
	private LocalDateTime createdAt;
	public Image(Integer id, String fileName, String originalFileName, Post post) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.originalFileName = originalFileName;
		this.post = post;
	}
	public Image() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public String getFileName() {
		return fileName;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public Post getPost() {
		return post;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	@PostRemove
	public void postRemove() throws IOException {
		FileUtil.deleteFile(this.fileName);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder{
		private Integer id;
		private String fileName;
		private String originalFileName;
		private Post post;
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}
		public Builder originalFileName(String originalFileName) {
			this.originalFileName = originalFileName;
			return this;
		}
		public Builder post(Post post) {
			this.post = post;
			return this;
		}
		public Image build() {
			return new Image(id, fileName, originalFileName, post);
		}
	}
}
