package com.sjy.shopping.model.dto;

import com.sjy.shopping.model.entity.Posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentAddDto {
	
	private String comment;
	private Posts post;
}
