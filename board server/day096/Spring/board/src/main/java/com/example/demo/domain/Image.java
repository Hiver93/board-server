package com.example.demo.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String originalFileName;
	@Column
	private String fileName;
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;
	@CreatedDate
	private LocalDateTime createdAt;
	@Builder
	public Image(Integer id, String originalFileName, String fileName, Post post) {
		super();
		this.id = id;
		this.originalFileName = originalFileName;
		this.fileName = fileName;
		this.post = post;
	}
}
