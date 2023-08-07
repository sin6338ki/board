package com.sjy.shopping.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sjy.shopping.model.entity.Posts;
import com.sjy.shopping.service.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final PostService postService;
	
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
	
//	@GetMapping("/list/{id}")
//	public String listContent() {
//		return "boardContent";
//	}
	
	//특정 게시물 조회
	@GetMapping("/post/{id}")
	public String findPost(@PathVariable(name="id") Long id, HttpServletResponse response, Model model, HttpSession session) throws Exception {
		Posts posts = postService.findPost(id);
		Model m = model.addAttribute("post", posts);
		return "boardContent";
	}

}
