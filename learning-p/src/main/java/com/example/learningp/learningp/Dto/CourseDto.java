package com.example.learningp.learningp.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CourseDto {
    
	@JsonProperty("name")
	private String courseName;
	
	@JsonProperty("category")
	private String category;
}

