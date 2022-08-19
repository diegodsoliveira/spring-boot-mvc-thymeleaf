package com.diego.springbootthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.diego.springbootthymeleaf.model")
public class SpringBootThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeleafApplication.class, args);
	}

}
