package com.upskill.cloudinaction.properties.controller;

import com.upskill.cloudinaction.properties.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.el.PropertyNotFoundException;


@RestController
@RequestMapping("/properties")
public class PropertiesController
{
	@Autowired
	private PropertyService propertyService;

	@GetMapping("/property/{key}")
	public String getProperty(@PathVariable String key)
	{
		return propertyService.getProperty(key);
	}

	@ExceptionHandler(PropertyNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException()
	{
		return new ResponseEntity<>(
				"Property not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
