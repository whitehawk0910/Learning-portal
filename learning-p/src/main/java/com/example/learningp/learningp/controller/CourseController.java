package com.example.learningp.learningp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningp.learningp.Dto.CourseDto;
import com.example.learningp.learningp.Dto.GetCourseDto;
import com.example.learningp.learningp.entity.CoursesCategory;
import com.example.learningp.learningp.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/createCourses")
	public ResponseEntity<?> createCourses(@Valid @RequestBody CourseDto courseDto) {
		boolean create = courseService.addCourse(courseDto);
		HashMap<String, String> response = new HashMap<>();
		if (create) {
			response.put("Message", "Course added successfully");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response.put("Message", "Some error occurred");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/viewCourses")
	public ResponseEntity<?> viewCourses(@Valid @RequestBody GetCourseDto getCourseDto) {
		Optional<List<CoursesCategory>> response = courseService.getCourse(getCourseDto);

		if (response.isEmpty()) {
			return new ResponseEntity<>(new HashMap<>().put("Message", "Category does not exists"),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@PostMapping("/view/{id}")
	public ResponseEntity<?> viewCourseById(@Valid @PathVariable Long id, @RequestBody GetCourseDto getCourseDto) {
		return courseService.getCourseId(id, getCourseDto);
	}
}