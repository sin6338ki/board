package com.sjy.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjy.shopping.model.entity.Comments;
import com.sjy.shopping.model.entity.Posts;

public interface CommentRepository extends JpaRepository<Comments, Long>{

	List<Comments> findAllByPosts(Posts post);
}
