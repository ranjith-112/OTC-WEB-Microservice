package com.login.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:global.properties")
public class GlobalConfiguration {
	Logger logger = LoggerFactory.getLogger(GlobalConfiguration.class);
	@Value("${auth.domain.admin}")
	private String admin;

	public String[] getAdmin() {
		return admin.split(",");

	}

}
