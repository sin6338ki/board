package com.sjy.shopping.model.entity;


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
import lombok.Setter;

@Getter
@Setter
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
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private int views;
	
	private String contents;
	
	private String title;
	
	@ManyToOne
	@JoinColumn(name="user_seq")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name="attach_id")
	private UploadFile file;
	
	@Builder
	public Posts(String title, String contents, Users users, Category category, UploadFile file) {
		this.users = users;
		this.title = title; 
		this.contents = contents;
		this.category = category;
		this.file = file;
	}
}
