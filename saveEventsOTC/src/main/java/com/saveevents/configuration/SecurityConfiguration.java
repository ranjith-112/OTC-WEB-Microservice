package com.saveevents.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.saveevents.jwt.utils.JwtFilter;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfiguration {
	Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
//	@Autowired
//	private UserDetailsService userDetailsService;
//	  @Autowired
//	   private JwtAuthenticationEntryPoint authenticationEntryPoint;
	  @Autowired
	  private JwtFilter jwtFilter;
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
//	@Bean
//	public AuthenticationProvider authprovider() {
//		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//		dao.setUserDetailsService(userDetailsService);
//		dao.setPasswordEncoder(passwordEncoder());
//		logger.debug("DaoAuthenticationProvider :{}", dao);
//		return dao;
//	}
//	
	@Bean
  public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      // Configure CORS settings (e.g., allowed origins, methods, headers)
      config.addAllowedOrigin("*");
      config.addAllowedMethod("*");
      config.addAllowedHeader("*");
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
  }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		//httpSecurity.headers().frameOptions().sameOrigin().and()
		httpSecurity
		.csrf().disable().authorizeRequests()
		.anyRequest()
		.authenticated()
//		.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
		//.and().formLogin()
		.and()
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
}

