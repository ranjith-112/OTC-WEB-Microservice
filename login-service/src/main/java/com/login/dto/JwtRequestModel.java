package com.login.dto;

import lombok.Data;

@Data
public class JwtRequestModel {
	private String username;
	private String password;
}