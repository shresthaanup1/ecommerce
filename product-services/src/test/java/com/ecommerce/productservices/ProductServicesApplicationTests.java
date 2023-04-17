package com.ecommerce.productservices;

import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.model.AddCategoryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //https://better-coding.com/springboot-resetting-h2-database-before-each-integration-test/
public class ProductServicesApplicationTests {

	//@Autowired
	//private CategoryController categoryController;

	@Autowired
	private CategoryRepository h2CategoryRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private CategoryDTO[] categories = new CategoryDTO[]{
			new CategoryDTO("Category1", "Category1 Description"),
			new CategoryDTO("Category2", "Category2 Description"),
			new CategoryDTO("Category3", "Category3 Description"),
			new CategoryDTO("Category4", "Category4 Description"),
			new CategoryDTO("randomCategory","I am random.")
	};

	@BeforeEach
	void setup(){
		for (int i = 0; i < categories.length; i++) {
			h2CategoryRepository.save(categories[i]);
			System.out.println("I am inserting data "+ i +" in h2");
		}
	}

	@AfterEach
	void clear(){
		h2CategoryRepository.deleteAll();
		System.out.println("I deleting data from h2");
	}

	@Test
	void contextLoads() {
		//assertNotNull(categoryController);
		assertNotNull(mockMvc);
	}

	@Test
	public void getCategoryByIdTest() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/product/category/5");
		mockMvc.perform(request)
				.andExpect(status().isFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.categoryName").value(categories[4].getCategoryName()))
				.andExpect(jsonPath("$.categoryDescription").value(categories[4].getCategoryDescription()));

		//.andExpectAll()
	}

	@Test
	public void categoryNotFoundTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/product/category/54");
		mockMvc.perform(request).andExpect(status().isNotFound());
	}

	@Test
	public void listCategoriesTest() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/product/category");
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()").value(categories.length))
				.andExpect(jsonPath("$.[?(@.id == \"2\" && @.categoryName == \"Category2\" && @.categoryDescription == \"Category2 Description\")]").exists());
	}

	//@Test
	public void addCategoryTest() throws Exception{
		//admin token
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MDEyNzA0NSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdfQ.REk1IfVgSrkvREl4kDpVc7-3v4qbggnGaQjyW5gj97jyxdjS3dZE4fbhMgp7_FQY_D9UkFCDQP3mRKrMpcdUww";

		//user token
		//String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjgwMTE5MjgxLCJhdXRob3JpdGllcyI6WyJST0xFX0tIT0kiXX0.04e9cyE-z8VRJXNvBvGQgQMoG32W_RpoIppnYOc5Ehx0OLfYvMdjlW3EMTJ8zQCz_gvmEdMce578MAW11TRKeQ";

		RequestBuilder request = MockMvcRequestBuilders.post("/product/category")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.content(objectMapper.writeValueAsString(new AddCategoryRequest("newCategory","I am newly added category.")));

				mockMvc.perform(request)
						.andExpect(status().isCreated())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.[?(@.id == \"6\" && @.categoryName == \"newCategory\" && @.categoryDescription == \"I am newly added category.\")]").exists());
						;
	}


	@Test
	public void addCategoryWithoutTokenTest() throws Exception{

		RequestBuilder request = MockMvcRequestBuilders.post("/product/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new AddCategoryRequest("newCategory","I am newly added category.")));

		mockMvc.perform(request)
				.andExpect(status().isForbidden());
	}

	//@Test
	public void deleteCategoryByIdTest() throws Exception {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MDEyNzA0NSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdfQ.REk1IfVgSrkvREl4kDpVc7-3v4qbggnGaQjyW5gj97jyxdjS3dZE4fbhMgp7_FQY_D9UkFCDQP3mRKrMpcdUww";

		RequestBuilder request = MockMvcRequestBuilders.delete("/product/category/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);

		mockMvc.perform(request)
				.andExpect(status().isNoContent());


		assertEquals(4,h2CategoryRepository.findAll().size());
	}
}


