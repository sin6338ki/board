package com.sjy.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.sjy.shopping.model.dto.PostReqDto;
import com.sjy.shopping.model.dto.PostUpdateDto;
import com.sjy.shopping.model.entity.Category;
import com.sjy.shopping.model.entity.Posts;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.repository.CategoryRepository;
import com.sjy.shopping.repository.PostRepository;
import com.sjy.shopping.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	
	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public Posts addPost(PostReqDto requestDto) {
		Category category = categoryRepository.findByName(requestDto.getCategory_name());
		Posts post = new Posts(requestDto.getTitle(), requestDto.getContents(), requestDto.getUser(), category);
		return postRepository.save(post);
	}
	
	@Transactional
	public List<Posts> findAllPosts(){
		return postRepository.findAll();
	}
	
	@Transactional
	public Posts findPost(@PathVariable Long id) {
		Posts post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("post 데이터가없습니다."));
		return post;
	}
	
	@Transactional 
	public List<Posts> findUserPosts(String id) {
		Users user = userRepository.findByUserid(id);
		List<Posts> posts = postRepository.findAllByUsers(user);
		return posts;
	}

	@Transactional
	public void updatePost(PostUpdateDto updateDto) {
		deletePost(updateDto.getPostId());
		Category category = categoryRepository.findByName(updateDto.getCategory_name());
		Posts post = new Posts(updateDto.getTitle(), updateDto.getContents(), updateDto.getUser(), category);
		postRepository.save(post);
	}
	
	@Transactional
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
