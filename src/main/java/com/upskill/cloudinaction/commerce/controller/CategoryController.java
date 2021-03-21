package com.upskill.cloudinaction.commerce.controller;

import com.upskill.cloudinaction.commerce.entity.Category;
import com.upskill.cloudinaction.commerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/commerce/categories")
public class CategoryController
{
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/all")
	public ResponseEntity<List<Category>> getAllCategories()
	{
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id)
	{
		return categoryService.getById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/category")
	public ResponseEntity<Category> createCategory(@RequestBody Category newCategory)
	{
		Category createdCategory = categoryService.create(newCategory);
		URI createdCategoryURI = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdCategory.getId())
				.toUri();
		return ResponseEntity.created(createdCategoryURI).body(createdCategory);
	}

	@PutMapping("/category/{id}")
	public ResponseEntity<Category> updateCategory(@RequestBody Category categoryToUpdate)
	{
		Category updatedCategory = categoryService.update(categoryToUpdate);
		return ResponseEntity.ok(updatedCategory);
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
	{
		categoryService.remove(id);
		return ResponseEntity.noContent().build();
	}
}
