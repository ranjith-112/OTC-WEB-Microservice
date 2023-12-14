package com.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class JwtResponseModel {
	private String token;
}
