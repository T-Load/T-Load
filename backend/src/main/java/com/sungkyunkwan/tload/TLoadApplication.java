package com.sungkyunkwan.tload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TLoadApplication {

	public static void main(String[] args) {
		SpringApplication.run(TLoadApplication.class, args);
	}

}
