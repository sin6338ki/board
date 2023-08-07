package com.sjy.shopping.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {

	private String userId;
	private String userPw;
	private String nick;
}
