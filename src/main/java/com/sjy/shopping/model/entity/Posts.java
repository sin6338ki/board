package com.sjy.shopping.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="posts")
public class Posts extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	private int views;
	
	private String contents;
	
	private String title;
	
	@ManyToOne
	@JoinColumn(name="user_seq")
	private Users users;
	
	@Builder
	public Posts(String title, String contents, Users users, Category category) {
		this.users = users;
		this.title = title; 
		this.contents = contents;
		this.category = category;
	}
	
}
