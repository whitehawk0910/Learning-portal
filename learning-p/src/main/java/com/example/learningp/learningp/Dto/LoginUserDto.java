package com.example.learningp.learningp.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class LoginUserDto {

	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
}
