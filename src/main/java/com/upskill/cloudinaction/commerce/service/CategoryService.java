package com.upskill.cloudinaction.commerce.service;

import com.upskill.cloudinaction.commerce.entity.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryService
{
	List<Category> getAllCategories();

	Optional<Category> getById(Long id);

	Category create(Category newCategory);

	Category update(Category categoryToUpdate);

	void remove(Long id);
}
