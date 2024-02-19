package com.example.learningp.learningp.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningp.learningp.Dto.LoginUserDto;
import com.example.learningp.learningp.Dto.RegisterUserDto;
import com.example.learningp.learningp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
		boolean register = userService.registerUser(registerUserDto);
		HashMap<String, String> response = new HashMap<>();

		if (register) {
			response.put("Message", "User registered successfully.");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response.put("Error", "User already exists");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto) {
		boolean login = userService.loginUser(loginUserDto);
		HashMap<String, String> response = new HashMap<>();

		if (login) {
			response.put("Message", "User logged in Successfully");
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} else {
			response.put("Message", "User authentication failed");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
