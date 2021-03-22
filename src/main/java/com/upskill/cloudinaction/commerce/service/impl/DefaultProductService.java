package com.upskill.cloudinaction.commerce.service.impl;

import com.google.common.collect.Lists;
import com.upskill.cloudinaction.commerce.entity.Product;
import com.upskill.cloudinaction.commerce.repository.ProductRepository;
import com.upskill.cloudinaction.commerce.service.ProductService;
import com.upskill.cloudinaction.commerce.util.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DefaultProductService implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts(String sortField, String sortOrder)
	{
		Optional<Sort> sortOption = SortUtils.buildSortOption(sortField, sortOrder);
		if (sortOption.isPresent())
		{
			return Lists.newArrayList(productRepository.findAll(sortOption.get()));
		}
		return Lists.newArrayList(productRepository.findAll());
	}

	@Override
	public List<Product> getProductsForPriceRange(Double minPrice, Double maxPrice)
	{
		if (minPrice > maxPrice)
		{
			throw new IllegalArgumentException("Min value in range cannot be greater than max");
		}
		return productRepository.findByPriceBetween(minPrice, maxPrice);
	}

	@Override
	public List<Product> searchProductsByName(String searchTerm)
	{
		return productRepository.findByNameContaining(searchTerm);
	}

	@Override
	public Optional<Product> getById(Long id)
	{
		return productRepository.findById(id);
	}

	@Override
	public Product create(Product newProduct)
	{
		return productRepository.save(newProduct);
	}

	@Override
	public Product update(Product productToUpdate)
	{
		return productRepository.save(productToUpdate);
	}

	@Override
	public void remove(Long id)
	{
		productRepository.deleteById(id);
	}

}
