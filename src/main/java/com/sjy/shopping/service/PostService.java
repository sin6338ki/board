package com.sjy.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.sjy.shopping.model.dto.PostReqDto;
import com.sjy.shopping.model.dto.PostUpdateDto;
import com.sjy.shopping.model.entity.Category;
import com.sjy.shopping.model.entity.Posts;
import com.sjy.shopping.model.entity.UploadFile;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.repository.AttachRepository;
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
	private final AttachRepository attachRepository;
	
	@Transactional
	public Posts addPost(PostReqDto requestDto) {
		Category category = categoryRepository.findByName(requestDto.getCategory_name());
		UploadFile file = attachRepository.save(requestDto.getFile());
		Posts post = new Posts(requestDto.getTitle(), requestDto.getContents(), requestDto.getUser(), category, file);
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
		attachRepository.delete(updateDto.getFile());
		Category category = categoryRepository.findByName(updateDto.getCategory_name());
		UploadFile file = attachRepository.save(updateDto.getFile());
		Posts post = new Posts(updateDto.getTitle(), updateDto.getContents(), updateDto.getUser(), category, file);
		postRepository.save(post);
	}
	
	@Transactional
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	
	@Transactional
	public List<Posts> findByCategoryByUserPosts(String category_name, Users user){
		Category category = categoryRepository.findByName(category_name);
		return postRepository.findAllByUsersAndCategory(user, category);
	}
	
	@Transactional
	public List<Posts> findByCategoryPosts(String category_name){
		Category category = categoryRepository.findByName(category_name);
		return postRepository.findAllByCategory(category);
	}

}
