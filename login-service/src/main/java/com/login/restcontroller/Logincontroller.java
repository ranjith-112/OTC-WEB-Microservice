package com.login.restcontroller;

import java.security.Principal;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.dto.JwtRequestModel;
import com.login.dto.JwtResponseModel;
import com.login.jwt.utils.TokenManager;

@RestController
public class Logincontroller {
	Logger logger = LoggerFactory.getLogger(Logincontroller.class);
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenManager tokenManager;

	@GetMapping("/dashboard")
	public ResponseEntity<JwtResponseModel> loadUserByUsername(@RequestParam ("username") String username) throws Exception {	
		logger.debug("invoking controller after successfull login ");
		logger.info("current user is "+username);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		Object[] roleObj = authorities.toArray();
		String role = roleObj[0].toString();
		logger.info("current user role is "+role);
		String jwtToken = null;
		if (userDetails != null) {
			jwtToken = tokenManager.generateToken(userDetails,role);
		}	
		
		return ResponseEntity.ok().body(new JwtResponseModel(jwtToken));
	}

//	@PostMapping("/login")
//	@CrossOrigin(origins={"http://localhost:4200/"})
//	public ResponseEntity<JwtResponseModel> login(@RequestBody JwtRequestModel jwtRequestModel) {
//		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequestModel.getUsername());
//		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//		Object[] roleObj = authorities.toArray();
//		String role = roleObj[0].toString();
//		JwtResponseModel jwtResponseModel = new JwtResponseModel();
//		
//		if (userDetails != null) {
//			//jwtResponseModel.setAuthenticated(true);
//			jwtResponseModel.setToken(tokenManager.generateToken(userDetails,role));
//			
//		}	
//		else {
//			jwtResponseModel.setToken(null);
//		}
//		return ResponseEntity.ok().body(jwtResponseModel);
//		
//	}
//	@GetMapping("/hello")
//	public ResponseEntity<String> hello(){
//		System.out.println("inside the hello endpoint");
//		return  ResponseEntity.ok().body("Helloooooo ");
//	}
}
