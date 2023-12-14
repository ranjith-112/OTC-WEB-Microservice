package com.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.login.entity.User;

public class UserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public UserPrincipal(User user) {
		super();
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public Long getEmpno() {
		return user.getEmpno();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getAdmins() {
		String role;
		if (Arrays.asList(user.getAdmin()).contains(user.getDepartment()) || user.isIsorganiser()) {
			role = "admin";
		} else {
			role = "user";
		}

		return role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> listauthority = new ArrayList<GrantedAuthority>();

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getAdmins());
		listauthority.add(authority);
		return listauthority;
	}

}

