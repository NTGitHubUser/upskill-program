package com.upskill.cloudinaction.properties;

import com.upskill.cloudinaction.properties.controller.PropertiesController;
import com.upskill.cloudinaction.properties.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.el.PropertyNotFoundException;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PropertiesController.class)
class PropertiesControllerTest
{
	private final static String EXISTING_PROPERTY_KEY = "existingPropertyKey";
	private final static String PROPERTY_VALUE = "propertyValue";

	private final static String ABSENT_PROPERTY_KEY = "absentPropertyKey";
	private final static String NOT_FOUND_PROPERTY_MESSAGE = "Property not found";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	public PropertyService propertyService;

	@Test
	public void shouldReturnPropertyValue_whenItIsPresent() throws Exception
	{
		given(propertyService.getProperty(EXISTING_PROPERTY_KEY)).willReturn(PROPERTY_VALUE);

		this.mockMvc
				.perform(get("/properties/property/" + EXISTING_PROPERTY_KEY))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(PROPERTY_VALUE)));
	}

	@Test
	public void shouldReturnNotFoundMessage_whenPropertyIsAbsent() throws Exception
	{
		given(propertyService.getProperty(ABSENT_PROPERTY_KEY)).willThrow(PropertyNotFoundException.class);

		this.mockMvc
				.perform(get("/properties/property/" + ABSENT_PROPERTY_KEY))
				.andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString("Test build")));
	}

}