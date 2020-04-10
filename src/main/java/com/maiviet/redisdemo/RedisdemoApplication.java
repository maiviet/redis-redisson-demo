package com.maiviet.redisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RedisdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisdemoApplication.class, args);
	}

}
