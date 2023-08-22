package com.sjy.shopping.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjy.shopping.model.entity.UploadFile;
import com.sjy.shopping.repository.AttachRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AttachService {
	private final AttachRepository attachRepository;

	@Transactional
	public UploadFile upload(UploadFile file) {
		return attachRepository.save(file);
	}
}
