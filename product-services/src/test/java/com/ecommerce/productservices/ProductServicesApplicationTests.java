package com.ecommerce.productservices;

import com.ecommerce.productservices.controller.CategoryController;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServicesApplicationTests {

	@Autowired
	private CategoryController categoryController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(categoryController);
		assertNotNull(mockMvc);
	}

	@Test
	public void getCategoryByIdTest() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/product/category/1");
		mockMvc.perform(request)
				.andExpect(status().isFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		//.andExpectAll()
	}
}
