package com.sjy.shopping.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sjy.shopping.model.entity.UserAuth;
import com.sjy.shopping.repository.UserAuthRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
	private final UserAuthRepository userAuthRepository;
	
//	private Boolean postAuth;
//	
//	private Boolean deleteAuth;
//	
//	private Boolean readAuth;
//	
//	private Boolean commentAuth;

	private boolean alreadyLoaded = false;
	
	@Override
	public void run(String... args) throws Exception {
		if(!alreadyLoaded) {			
			UserAuth auth0 = new UserAuth(0L, "0", false, false, true, false);
			UserAuth auth1 = new UserAuth(1L, "1", false, false, true, true);
			UserAuth auth2 = new UserAuth(2L, "2", true, true, true, true);
			userAuthRepository.save(auth0);
			userAuthRepository.save(auth1);
			userAuthRepository.save(auth2);
			alreadyLoaded = true;
		}
	}
}
