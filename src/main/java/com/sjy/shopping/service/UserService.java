package com.sjy.shopping.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjy.shopping.model.dto.JoinDto;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Transactional
	public Users login(Users user) {
		return userRepository.findByUseridAndUserpw(user.getUserid(), user.getUserpw());
	}
	
	@Transactional
	public Users join(Users user) {
//		Users user2 = new Users(user.getUserId(), user.getUserPw(), user.getNick());
		return userRepository.save(user);
	}
	
	@Transactional
	public Users updateUserInfo(HttpSession session, HttpServletRequest request) {
		//기존 정보 삭제
		Users deleteUser = (Users)session.getAttribute("loginUser");
		userRepository.delete(deleteUser);
		//수정된 정보로 회원 추가
		Users user = (Users)request.getAttribute("updateUser");
		return userRepository.save(user);
	}
	
}
