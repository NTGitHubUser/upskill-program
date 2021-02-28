package com.upskill.cloudinaction.properties.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.Optional;


@Service
@PropertySource("classpath:application.properties")
public class PropertyService
{
	@Autowired
	private Environment environment;

	public String getProperty(final String key)
	{
		return Optional.ofNullable(environment.getProperty(key))
				.orElseThrow(PropertyNotFoundException::new);
	}
}
