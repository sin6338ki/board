package com.sjy.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sjy.shopping.model.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	@Query("select u from users u where u.userid = :userid and u.userpw = :userpw")
	Users findByUseridAndUserpw(@Param("userid")String userid, @Param("userpw")String userpw);
}
