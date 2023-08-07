package com.sjy.shopping.web;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjy.shopping.model.entity.UserAuth;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class userController {

	private final UserService userService;
	
	//로그인
	@PostMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		Users loginUser = new Users(userid, userpw);
		Users user = userService.login(loginUser);
//		System.out.println(user.getNick());
//		System.out.println(user.getUserid());
//		System.out.println(user.getUserAuth().getLevel());
		
		//세션에 로그인한 유저 정보 저장
		if(user!=null) session.setAttribute("loginUser", user);
		response.sendRedirect("/list");			
	}
	
	//회원가입
	@PostMapping("/user/add")
	public void join(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String nick = request.getParameter("nick");
		UserAuth userauth =  new UserAuth();
		userauth.setId(5L);
		Users joinUser = new Users(userid, userpw, nick, userauth);
		userService.join(joinUser);
		response.sendRedirect("/list");
	}
	
	//로그아웃
	@GetMapping("/logout")
	public void logout(HttpServletResponse response, HttpSession session) throws Exception {
		session.invalidate();
		response.sendRedirect("/list");
	}
}
