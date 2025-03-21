package com.example.demo.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
	@CreatedDate
	private LocalDateTime createdAt;
	public Image(Integer id, String fileName, String originalFileName) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.originalFileName = originalFileName;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}	
}
