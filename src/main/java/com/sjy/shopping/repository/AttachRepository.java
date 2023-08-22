package com.sjy.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjy.shopping.model.entity.UploadFile;

public interface AttachRepository extends JpaRepository<UploadFile, Long> {

}
