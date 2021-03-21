package com.upskill.cloudinaction.commerce.service;

import com.upskill.cloudinaction.commerce.entity.Product;

import java.util.List;


public interface ProductCategoriesAssignmentService
{
	void assignProductsToCategory(List<Long> productIds, Long categoryId);

	void removeProductsFromCategory(List<Long> productIds, Long categoryId);

	List<Product> getAllProductsForCategory(Long categoryId);

}
