package com.sjy.shopping.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="uploadfile")
public class UploadFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attach_id")
	private Long id;
	
	@Column(name = "upload_filename")
	private String uploadFilename; //작성자가 업로드한 파일명
	
	@Column(name = "store_filename")
	private String storeFilename; //서버 내부에서 관리하는 파일명
	
	@Builder
	public UploadFile(String uploadFilename, String storeFilename) {
		this.uploadFilename = uploadFilename;
		this.storeFilename = storeFilename;
	}
}
