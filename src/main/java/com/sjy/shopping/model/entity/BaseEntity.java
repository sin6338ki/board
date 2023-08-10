package com.sjy.shopping.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
//상속 계층 구조에서 공통 속성과 매핑 정보를 가진 부모 클래스를 정의할 때 사용 
//이 어노테이션을 부모 클래스 위에 선언하면, 자식 엔터티들은 이 부모 클래스의 속성과 매핑 정보를 상속받음
@EntityListeners(AuditingEntityListener.class)
//엔터티의 생성일과 수정일을 자동으로 관리
public class BaseEntity {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createAt;
	
//	@CreatedBy
//	@Column(updatable = false)
//	private String createdBy;
	
	@LastModifiedDate
	@Column(updatable = false)
	private LocalDateTime modifiedAt;
	
//	@LastModifiedBy
//	private String modifiedBy;

}
