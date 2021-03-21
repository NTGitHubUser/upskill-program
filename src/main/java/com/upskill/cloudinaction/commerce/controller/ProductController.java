package com.upskill.cloudinaction.commerce.controller;

import com.upskill.cloudinaction.commerce.entity.Product;
import com.upskill.cloudinaction.commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/commerce/products")
public class ProductController
{
	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts()
	{
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/priceRange")
	public ResponseEntity<List<Product>> getProductsInPriceRange(@RequestParam Double min, @RequestParam Double max)
	{
		return ResponseEntity.ok(productService.getProductsForPriceRange(min, max));
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id)
	{
		return productService.getById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String searchTerm)
	{
		return ResponseEntity.ok(productService.searchProductsByName(searchTerm));
	}


	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product newProduct)
	{
		Product createdProduct = productService.create(newProduct);
		URI uriForCreatedProduct = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdProduct.getId())
				.toUri();
		return ResponseEntity.created(uriForCreatedProduct).body(createdProduct);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product productToUpdate)
	{
		Product updatedProduct = productService.update(productToUpdate);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
	{
		productService.remove(id);
		return ResponseEntity.noContent().build();
	}

}
