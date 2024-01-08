package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.app.dao.CategoryDao;
import com.app.dto.CategoryDTO;
import com.app.entities.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao catDao;
	@Autowired
	private ModelMapper mapper;
	@Override
	public CategoryDTO addNewCategory(CategoryDTO category) {
		
		return mapper.map(catDao.save(mapper.map(category, Category.class)), CategoryDTO.class);
	}
	
}
