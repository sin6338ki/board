package com.sjy.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
@EnableJpaAuditing
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}
//
//@Bean
//public AuditorAware<String> auditorProvider(){
//	 return () -> Optional.of(UUID.randomUUID().toString());
//	}
}
