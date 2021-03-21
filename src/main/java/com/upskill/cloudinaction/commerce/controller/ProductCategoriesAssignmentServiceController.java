package com.upskill.cloudinaction.commerce.controller;

import com.upskill.cloudinaction.commerce.entity.Product;
import com.upskill.cloudinaction.commerce.service.ProductCategoriesAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/commerce/assignments")
public class ProductCategoriesAssignmentServiceController
{
	@Autowired
	private ProductCategoriesAssignmentService productCategoriesAssignmentService;

	@GetMapping("/category/products")
	public ResponseEntity<List<Product>> getAllProductsForCategory(@RequestParam Long categoryId)
	{
		List<Product> products = productCategoriesAssignmentService.getAllProductsForCategory(categoryId);
		return ResponseEntity.ok(products);
	}

	@PostMapping("/category/{categoryId}/assign")
	public ResponseEntity<Void> assignProductToCategory(@PathVariable Long categoryId, @RequestBody List<Long> productIds)
	{
		productCategoriesAssignmentService.assignProductsToCategory(productIds, categoryId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/category/{categoryId}/remove")
	public ResponseEntity<Void> removeProductsFromCategory(@PathVariable Long categoryId, @RequestBody List<Long> productIds)
	{
		productCategoriesAssignmentService.removeProductsFromCategory(productIds, categoryId);
		return ResponseEntity.ok().build();
	}

}
