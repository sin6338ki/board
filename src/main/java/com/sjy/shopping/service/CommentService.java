package com.sjy.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjy.shopping.model.dto.CommentAddDto;
import com.sjy.shopping.model.entity.Comments;
import com.sjy.shopping.model.entity.Posts;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.repository.CommentRepository;
import com.sjy.shopping.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	
	@Transactional
	public Comments addComment(Users user, CommentAddDto dto){ 
		Comments comment = new Comments(user, dto.getComment(), dto.getPost());
		return commentRepository.save(comment);
	}
	
	@Transactional
	public List<Comments> findAllByPosts(long id){
		Posts post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("post 데이터가없습니다."));
		return commentRepository.findAllByPosts(post);
	}
}
