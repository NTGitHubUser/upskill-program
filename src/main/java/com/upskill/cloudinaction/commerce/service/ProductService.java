package com.upskill.cloudinaction.commerce.service;

import com.upskill.cloudinaction.commerce.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService
{
	List<Product> getAllProducts(String sortField, String sortOrder);

	List<Product> getProductsForPriceRange(Double minPrice, Double maxPrice);

	List<Product> searchProductsByName(String searchTerm);

	Optional<Product> getById(Long id);

	Product create(Product newProduct);

	Product update(Product productToUpdate);

	void remove(Long id);
}
