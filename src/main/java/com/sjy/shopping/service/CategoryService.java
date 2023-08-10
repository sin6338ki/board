package com.sjy.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjy.shopping.model.entity.Category;
import com.sjy.shopping.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;

	@Transactional
	public List<Category> findAllCategory(){ 
		System.out.println(categoryRepository.findAll().get(0).getCategory_name());
		return categoryRepository.findAll();
	}
}
