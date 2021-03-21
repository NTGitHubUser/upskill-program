package com.upskill.cloudinaction.commerce.service.impl;

import com.google.common.collect.Lists;
import com.upskill.cloudinaction.commerce.entity.Category;
import com.upskill.cloudinaction.commerce.repository.CategoryRepository;
import com.upskill.cloudinaction.commerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DefaultCategoryService implements CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories()
	{
		return Lists.newArrayList(categoryRepository.findAll());
	}

	@Override
	public Optional<Category> getById(Long id)
	{
		return categoryRepository.findById(id);
	}

	@Override
	public Category create(Category newCategory)
	{
		return categoryRepository.save(newCategory);
	}

	@Override
	public Category update(Category categoryToUpdate)
	{
		return categoryRepository.save(categoryToUpdate);
	}

	@Override
	public void remove(Long id)
	{
		categoryRepository.deleteById(id);
	}
}
