package com.sjy.shopping.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.sjy.shopping.attachfile.FileStore;
import com.sjy.shopping.model.dto.PostReqDto;
import com.sjy.shopping.model.dto.PostUpdateDto;
import com.sjy.shopping.model.entity.Posts;
import com.sjy.shopping.model.entity.UploadFile;
import com.sjy.shopping.model.entity.Users;
import com.sjy.shopping.service.AttachService;
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
	public void post(HttpServletRequest request, HttpSession session, HttpServletResponse response, @RequestPart("attach") MultipartFile file) throws Exception {
		Users user = (Users)session.getAttribute("loginUser");	
		
		//첨부파일 처리
		UploadFile attachFile = null;
		try {
			attachFile = FileStore.storeFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PostReqDto requestDto = new PostReqDto(request.getParameter("title"), request.getParameter("contents"), user, request.getParameter("category_name"), attachFile);
		System.out.println(requestDto.getCategory_name());
		log.info("view to controll with post");
		postService.addPost(requestDto);
		response.sendRedirect("/list");
	}
	
	//전체 글 조회
	@GetMapping("/post/findall")
	public List<Posts> findAllPosts(){
		return postService.findAllPosts();
	}
	
	//해당 id가 작성한 글 조회 
	@GetMapping("/post/find/{userid}")
	public List<Posts> findUserPosts(@PathVariable(name="userid") String id){
		return postService.findUserPosts(id);
	}
	
	//글 수정
    @PostMapping("/post/update/submit")
    public void updatePost(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestPart("attach") MultipartFile file) throws Exception {
    	Users user = (Users)session.getAttribute("loginUser");
    	long post_id = Long.parseLong(request.getParameter("post_id"));
    	
    	//첨부파일 처리
		UploadFile attachFile = null;
		try {
			attachFile = FileStore.storeFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	PostUpdateDto updateDto = new PostUpdateDto(request.getParameter("title"), request.getParameter("contents"), user, request.getParameter("category_name"), post_id, attachFile);
    	postService.updatePost(updateDto);
    	String userId = user.getUserid();
    	response.sendRedirect("/admin/" + userId);
    }
    
    //글 삭제
    @GetMapping("/post/delete/{postid}")
    public void deletePost(@PathVariable(name="postid") Long postId, HttpServletResponse response, HttpSession session, HttpServletRequest request) throws Exception {
    	postService.deletePost(postId);
    	Users user = (Users)session.getAttribute("loginUser");
    	String userId = user.getUserid();
    	response.sendRedirect("/admin/" + userId);
    }
    
    //카테고리별 유저별 게시글 불러오기
    @GetMapping("post/findbycategoryanduser/{category}")
    public List<Posts> findByCategoryByUserPosts(@PathVariable(name="category") String category_name, HttpSession session){
    	Users user = (Users)session.getAttribute("loginUser");
    	return postService.findByCategoryByUserPosts(category_name, user);
    }
    
    //카테고리별 게시글 불러오기
    @GetMapping("post/findbycategory/{category}")
    public List<Posts> findByCategoryPosts(@PathVariable(name="category") String category_name){
    	return postService.findByCategoryPosts(category_name);
    }
    
    //첨부파일 다운로드
    @GetMapping("/attach/{id}")
    public ResponseEntity<UrlResource> download(@PathVariable long id) throws MalformedURLException{
    	Posts post = postService.findPost(id);
    	String storeFilename = post.getFile().getStoreFilename();
    	String uploadFilename = post.getFile().getUploadFilename();
    	
    	UrlResource urlResource = new UrlResource("file:" + FileStore.getPullPath(storeFilename));
    	
    	//한글 인코딩
    	String encodedUploadFileName = UriUtils.encode(uploadFilename, StandardCharsets.UTF_8);
    	String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
    	
    	//header에 CONTENT_DIPOSITION 설정을 통해 클릭시 다운로드 설정
    	return ResponseEntity.ok()
    			.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
    			.body(urlResource);

    }
}
