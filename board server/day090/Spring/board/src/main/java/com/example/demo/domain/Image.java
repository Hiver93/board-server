package com.example.demo.domain;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.util.ImageUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostRemove;
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
	private String fileName;
	@Column
	private String originalFileName;
	@OneToOne(fetch = FetchType.LAZY)
	private Post post;
	@CreatedDate
	private LocalDateTime createdAt;
	@Builder
	public Image(Integer id, String fileName, String originalFileName, Post post) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.originalFileName = originalFileName;
		this.post = post;
	}
	
	@PostRemove
	public void postRemove() throws IOException {
		ImageUtil.deleteImage(fileName);
	}
}
