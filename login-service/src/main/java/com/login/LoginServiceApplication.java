package com.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication
public class LoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginServiceApplication.class, args);
	}
	
	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer paginationCustomizer() {
		return pageableResolver -> {
			pageableResolver.setOneIndexedParameters(true);
		};

	}
}
