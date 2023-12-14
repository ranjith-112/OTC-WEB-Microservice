package com.login.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.login.UserPrincipal;
import com.login.configuration.GlobalConfiguration;
import com.login.entity.User;
import com.login.exceptionhandling.UsernameNotFoundException1;
import com.login.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(UserAuthService.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GlobalConfiguration globalConfiguration;

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username)  {
		logger.debug("Current User :{}", username);
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException1("User not found !");
		}
		user.setAdmin(globalConfiguration.getAdmin());
		logger.debug("user role :{} ", user.getAdmin());
		logger.debug("User deatils :{}", user);
		if (user == null) {
			throw new UsernameNotFoundException1("User not found !");
		}
		return new UserPrincipal(user);
	}

}