package com.sjy.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sjy.shopping.model.entity.Category;
import com.sjy.shopping.model.entity.Posts;
import com.sjy.shopping.model.entity.Users;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

	List<Posts> findAllByUsers(Users user);
	
	List<Posts> findAllByUsersAndCategory(Users user, Category category);
	
	List<Posts> findAllByCategory(Category category);
}