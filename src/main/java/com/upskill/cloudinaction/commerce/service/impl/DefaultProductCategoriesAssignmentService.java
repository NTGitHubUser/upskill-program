package com.upskill.cloudinaction.commerce.service.impl;

import com.upskill.cloudinaction.commerce.entity.Category;
import com.upskill.cloudinaction.commerce.entity.Product;
import com.upskill.cloudinaction.commerce.repository.CategoryRepository;
import com.upskill.cloudinaction.commerce.repository.ProductRepository;
import com.upskill.cloudinaction.commerce.service.ProductCategoriesAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;


@Service
public class DefaultProductCategoriesAssignmentService implements ProductCategoriesAssignmentService
{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void assignProductsToCategory(List<Long> productIds, Long categoryId)
	{
		Optional<Category> categoryToAssign = categoryRepository.findById(categoryId);
		categoryToAssign.ifPresent(category -> addProductsToCategory(productIds, category));
	}

	@Override
	public List<Product> getAllProductsForCategory(Long categoryId)
	{
		return categoryRepository
				.findById(categoryId)
				.map(category -> newArrayList(category.getProducts()))
				.orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found!"));
	}

	@Override
	public void removeProductsFromCategory(List<Long> productIds, Long categoryId)
	{
		Optional<Category> categoryToAssign = categoryRepository.findById(categoryId);
		categoryToAssign.ifPresentOrElse(category -> removeProductsFromCategory(productIds, category),
										 () -> new EntityNotFoundException("Category with id " + categoryId + " not found!"));
	}

	private void addProductsToCategory(List<Long> productIds, Category category)
	{
		Set<Product> productsToAssign = getAllProductsForIds(productIds);
		productsToAssign.forEach(product -> saveCategoryForProduct(product, category));
	}


	private void saveCategoryForProduct(Product product, Category category)
	{
		Set<Category> productCategories = product.getCategories();
		productCategories.addAll(newArrayList(category));
		product.setCategories(productCategories);
		productRepository.save(product);
	}


	private void removeProductsFromCategory(List<Long> productIds, Category category)
	{
		Set<Product> productsToRemove = getAllProductsForIds(productIds);
		productsToRemove.forEach(product -> product.getCategories().remove(category));
		productRepository.saveAll(productsToRemove);
	}

	private Set<Product> getAllProductsForIds(List<Long> productIds)
	{
		return newHashSet(productRepository.findAllById(productIds));
	}

}
