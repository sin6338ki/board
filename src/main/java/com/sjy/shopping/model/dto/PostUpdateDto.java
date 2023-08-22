package com.sjy.shopping.model.dto;

import com.sjy.shopping.model.entity.UploadFile;
import com.sjy.shopping.model.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDto {
	
	private String title;
	private String contents;
	private Users user;
	private String category_name;
	private long postId;
	private UploadFile file;

}
