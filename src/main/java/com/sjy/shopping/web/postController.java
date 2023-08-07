package com.sjy.shopping.web;

import java.io.IOException;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjy.shopping.model.dto.PostReqDto;
import com.sjy.shopping.model.entity.Posts;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.repository.PostRepository;
import com.sjy.shopping.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class postController {
	
	private final PostService postService;
	
	//글 등록
	@PostMapping("/post/add")
	public void post(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {
		Users user = (Users)session.getAttribute("loginUser");	
		PostReqDto requestDto = new PostReqDto(request.getParameter("title"), request.getParameter("contents"), user, request.getParameter("category_name"));
		System.out.println(requestDto.getCategory_name());
		log.info("view to controll with post");
		postService.addPost(requestDto);
		response.sendRedirect("/list");
	}
	
	//전체 글 조회
	@GetMapping("/post")
	public List<Posts> findAllPosts(){
		return postService.findAllPosts();
	}
}
