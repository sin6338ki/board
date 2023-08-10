package com.sjy.shopping.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjy.shopping.model.entity.Category;
import com.sjy.shopping.service.CategoryService;
import com.sjy.shopping.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CategoryController {
	
	private final CategoryService categoryService;
	
	//전체 목록 불러오기 
	@GetMapping("/category/select")
	public List<Category> findAllCategory(){
		System.out.println("ssss");
		return categoryService.findAllCategory();
	}
	
}
