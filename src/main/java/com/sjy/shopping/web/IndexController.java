package com.sjy.shopping.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sjy.shopping.model.entity.Posts;
import com.sjy.shopping.repository.PostRepository;
import com.sjy.shopping.service.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final PostService postService;
	private final PostRepository postRepository;
	
	@GetMapping("/list")
	public String list() {
		return "board";
	}
	
	@GetMapping("/upload")
	public String upload() {
		return "write";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	//특정 게시물 조회
	@GetMapping("/post/{id}")
	public String findPost(@PathVariable(name="id") Long id, HttpServletResponse response, Model model, HttpSession session) throws Exception {
		Posts posts = postService.findPost(id);
		
		//조회수 1증가
		posts.setViews(posts.getViews()+1);
		postRepository.save(posts);
		
		Model m = model.addAttribute("post", posts);
		return "boardContent";
	}
	
	//마이페이지 이동
	@GetMapping("/admin/{id}")
	public String admin(@PathVariable(name="id") String id, HttpSession session) {
		return "admin";
	}
	
	//게시물 수정 페이지로 이동 
	@GetMapping("/post/update/{id}")
	public String updatePost(@PathVariable(name="id") Long id, Model model) {
		Posts posts = postService.findPost(id);
		Model m = model.addAttribute("post", posts);
		return "update";
	}
	
	//회원정보 수정 페이지로 이동 
	@GetMapping("/update")
	public String updateUser() {
		return "userInfoUpdate";
	}
}
