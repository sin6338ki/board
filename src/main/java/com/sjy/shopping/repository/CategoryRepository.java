package com.sjy.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sjy.shopping.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("select u from category u where u.category_name=:category_name")
	Category findByName(@Param("category_name")String category_name);
	
	
}
