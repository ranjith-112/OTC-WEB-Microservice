package com.getallevent;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableCaching
public class GetAllEventsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetAllEventsServiceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetailsService userDetailsService=new UserAuthService();
//		return userDetailsService;
//	}
}
