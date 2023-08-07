package com.sjy.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjy.shopping.model.entity.UserAuth;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long>{
	
	

}
