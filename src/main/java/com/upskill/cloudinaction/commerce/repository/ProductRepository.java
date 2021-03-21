package com.upskill.cloudinaction.commerce.repository;

import com.upskill.cloudinaction.commerce.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long>
{

	List<Product> findByNameContaining(String name);

	List<Product> findByPriceBetween(Double startRange, Double endRange);


}
