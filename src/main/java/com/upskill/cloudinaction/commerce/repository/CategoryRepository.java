package com.upskill.cloudinaction.commerce.repository;

import com.upskill.cloudinaction.commerce.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoryRepository extends CrudRepository<Category, Long>
{
	List<Category> findByNameContaining(String name);
}
