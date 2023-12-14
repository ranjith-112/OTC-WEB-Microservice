package com.deleteevent.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
@Component
public class UserDTO implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String role;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		ArrayList<GrantedAuthority> listauthority = new ArrayList<GrantedAuthority>();

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getRole());
		listauthority.add(authority);
		return listauthority;
	}
	public void setRole(String role) {
		this.role=role;
	}
	public String getRole() {
		return this.role;
	}
//	public ArrayList<String> getRole() {
//		return role;
//	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public String setUsername(String username) {
		return this.username=username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
