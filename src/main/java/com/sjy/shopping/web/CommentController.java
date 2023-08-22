package com.sjy.shopping.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjy.shopping.model.dto.CommentAddDto;
import com.sjy.shopping.model.entity.Comments;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.service.CommentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;
	
	//댓글 추가
	@PostMapping("comment/add")
	public void addComment(@RequestBody CommentAddDto dto, HttpSession session, HttpServletResponse response) throws Exception{
		Users user = (Users)session.getAttribute("loginUser");
		commentService.addComment(user, dto);
		response.sendRedirect("/post/" + dto.getPost().getId());
	}
	
	//해당 게시글에 대한 전체 댓글 조회
	@GetMapping("comment/{id}")
	public List<Comments> findAllByPosts(@PathVariable(name="id") Long id) {
		System.out.println("id : " + id);
		return commentService.findAllByPosts(id);
	}
	
}
