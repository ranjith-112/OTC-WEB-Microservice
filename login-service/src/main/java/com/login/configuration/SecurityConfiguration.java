package com.login.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.login.jwt.utils.JwtAuthenticationEntryPoint;
import com.login.jwt.utils.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfiguration {
	Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
	@Autowired
	private UserDetailsService userDetailsService;
	  @Autowired
	   private JwtAuthenticationEntryPoint authenticationEntryPoint;
	  @Autowired
	  private JwtFilter jwtFilter;
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	@Bean
	public AuthenticationProvider authprovider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService);
		dao.setPasswordEncoder(passwordEncoder());
		logger.debug("DaoAuthenticationProvider :{}", dao);
		return dao;
	}


//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.headers().frameOptions().sameOrigin().and().authorizeRequests()
//
//		//.csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
//		.anyRequest()
//		.authenticated()
//		//.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//		.and()
//		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//		//.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
//		.formLogin().successHandler(authenticationSuccessHandler())
//	;
//				
//		return httpSecurity.build();
//	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().frameOptions().sameOrigin().and()
		.cors().and()
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .and()
		.csrf().disable().authorizeRequests().antMatchers("/login","/dashboard").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		//.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
		.formLogin().successHandler(authenticationSuccessHandler());	
		return httpSecurity.build();
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
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
}

