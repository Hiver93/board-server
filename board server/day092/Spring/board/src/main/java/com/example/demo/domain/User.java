package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Getter
@SQLDelete(sql = "UPDATE users SET deleted_at = now()")
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String nickname;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.MERGE})
	private List<Post> postList;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;
	@Column
	private LocalDateTime deletedAt;
	@Builder
	public User(Integer id, String username, String password, String nickname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean isSameUser(User user) {
		return this.id.equals(user.id);
	}
	public boolean isActivated() {
		return this.deletedAt == null;
	}
}
