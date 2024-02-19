package com.example.learningp.learningp.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.learningp.learningp.Dto.CategoryDto;
import com.example.learningp.learningp.entity.Category;
import com.example.learningp.learningp.mapper.CourseMapper;
import com.example.learningp.learningp.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CourseMapper courseMapper;

	public boolean addCategory(CategoryDto categoryDto) {

		try {
			Category toAdd = courseMapper.categoryDtoToCategory(categoryDto);
			if (toAdd == null) {
				log.error("Failed to add category. Received null Category object from mapper.");
				return false;
			}
			categoryRepository.save(toAdd);
			log.info("Category added successfully: {}", toAdd);
			return true;

		} catch (Exception e) {
			log.error("Failed to add category. Category already exists.", e);
			return false;
		}
	}

	public HashMap<String, List<Category>> getCategory() {
		HashMap<String, List<Category>> response = new HashMap<>();
		response.put("Message", categoryRepository.findAll());
		return response;
	}
}