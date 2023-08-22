package com.sjy.shopping.attachfile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sjy.shopping.model.entity.UploadFile;

@Component
public class FileStore {

	//루트 경로 불러오기
	private final static String rootPath = System.getProperty("user.dir");
	
	//프로젝트 루트 경로에 있는 files 디렉토리
	private final static String fileDir = rootPath + "/src/main/resources/static/files/";
	
	public static String getPullPath(String filename) { return fileDir + filename;}
	
	public static UploadFile storeFile(MultipartFile multipartFile) throws IOException{
		if(multipartFile.isEmpty()) {
			return null;
		}
		
		String originalFilename = multipartFile.getOriginalFilename();
		//작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명으로 변환
		//파일명 중복되지 않도록 UUID로 정하고 확장자는 그대로!
		String storeFilename = UUID.randomUUID() + "." + extractExt(originalFilename);
		
		//파일을 저장하는 부분 : 파일 경로 + storeFilename에 저장
		multipartFile.transferTo(new File(getPullPath(storeFilename)));
		
		return new UploadFile(originalFilename, storeFilename);
	}
	
	
    // 확장자 추출
    private static String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
	
}
