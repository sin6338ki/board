package com.sjy.shopping.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjy.shopping.model.dto.JoinDto;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.repository.UserRepository;

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
	
}
