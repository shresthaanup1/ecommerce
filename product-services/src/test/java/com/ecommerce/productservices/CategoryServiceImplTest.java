package com.ecommerce.productservices;

import com.ecommerce.productservices.dao.CategoryDAO;
import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.exception.CategoryNotFoundException;
import com.ecommerce.productservices.exception.JsonParameterNotValidException;
import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import com.ecommerce.productservices.model.UpdateCategoryRequest;
import com.ecommerce.productservices.service.CategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) //runs every test inside the Runner class without scanning components
public class CategoryServiceImplTest {
    @Mock//mimic the behaviour of CategoryDAO without any logic in it
    private CategoryDAO categoryDAO;

    @InjectMocks// creates a categoryServiceImpl object and injects mock into it
    private CategoryServiceImpl categoryService;

    @Test
    public void addCategoryTest() {
        // Create an instance of the AddCategoryRequest with valid input data
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest("TestCategory", "Test description");

        // Mock the CategoryDAO to return a CategoryDTO when save() is called
        CategoryDTO categoryDTO = new CategoryDTO( "TestCategory", "Test description");
        when(categoryDAO.save(any(CategoryDTO.class))).thenReturn(categoryDTO);

        // Call the addCategory() method
        Category category = categoryService.addCategory(addCategoryRequest);

        // Assert that the returned Category object has the correct values
        assertEquals(categoryDTO.getCategoryName(), category.getCategoryName());
        assertEquals(categoryDTO.getCategoryDescription(), category.getCategoryDescription());

        // Verify that save() method of CategoryDAO was called with the correct parameter
        ArgumentCaptor<CategoryDTO> categoryDTOArgumentCaptor = ArgumentCaptor.forClass(CategoryDTO.class);
        verify(categoryDAO, times(1)).save(categoryDTOArgumentCaptor.capture());
        CategoryDTO savedCategoryDTO = categoryDTOArgumentCaptor.getValue();
        assertEquals(addCategoryRequest.getCategoryName(), savedCategoryDTO.getCategoryName());
        assertEquals(addCategoryRequest.getCategoryDescription(), savedCategoryDTO.getCategoryDescription());

        verifyNoMoreInteractions(categoryDAO);
    }

    @Test
    public void listCategoriesTest() {
        // Create a list of CategoryDTOS
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        categoryDTOs.add(new CategoryDTO("TestCategory1","Test1 description"));
        categoryDTOs.add(new CategoryDTO("TestCategory2", "Test2 description"));

        // Mock the CategoryDAO to return a list of CategoryDTOs when findAll() is called
        when(categoryDAO.findAll()).thenReturn(categoryDTOs);

        // Call the listCategories() method
        List<Category> categories = categoryService.listCategories();

        // Assert that the returned list of Category objects has the correct number of elements
        assertEquals(categoryDTOs.size(),categories.size());

        // Assert that the returned Category objects have the correct values
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            CategoryDTO categoryDTO = categoryDTOs.get(i);
            assertEquals(categoryDTO.getId(), category.getId());
            assertEquals(categoryDTO.getCategoryName(), category.getCategoryName());
            assertEquals(categoryDTO.getCategoryDescription(),category.getCategoryDescription());
        }
    }

    @Test
    public void getCategoryByIdTest() {
        CategoryDTO categoryDTO = new CategoryDTO( "TestCategory", "This is a test category");
        Category expectedCategory = new Category(categoryDTO.getId(), categoryDTO.getCategoryName(), categoryDTO.getCategoryDescription());

        when(categoryDAO.findById(1L)).thenReturn(Optional.of(categoryDTO));
        Category actualCategory = categoryService.getCategoryById(1L);
        assertEquals(expectedCategory.getCategoryName(), actualCategory.getCategoryName());
        assertEquals(expectedCategory.getCategoryDescription(), actualCategory.getCategoryDescription());
    }

    @Test
    public void getCategoryByIdTestWhenIdNotFound() {
        when(categoryDAO.findById(2L)).thenReturn(Optional.empty());
        // Approach 1
        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(2L));

        // Approach 2
//        try {
//            Category actualCategory = categoryService.getCategoryById(2L);
//            fail("Should have thrown but didn't throw");
//        } catch (Exception e) {
//            assertEquals(CategoryNotFoundException.class, e.getClass());
//        }
    }
    @Test
    public void deleteByIdTest() {
        CategoryDTO categoryDTO = new CategoryDTO( "TestCategory", "This is a test category");
        Category expectedCategory = new Category(categoryDTO.getId(), categoryDTO.getCategoryName(), categoryDTO.getCategoryDescription());

        when(categoryDAO.findById(1L)).thenReturn(Optional.of(categoryDTO));
        categoryService.deleteCategoryById(1L);
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(categoryDAO, times(1)).deleteById(idArgumentCaptor.capture());
        Long deletedId = idArgumentCaptor.getValue();
        assertEquals(1L, deletedId);
    }

    @Test
    public void deleteByIdTestWhenIdNotFound() {
        when(categoryDAO.findById(2L)).thenReturn(Optional.empty());
        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategoryById(2L));
    }

    @Test
    public void updateCategoryTest() {
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(1L,"TestCategory", "Test description");
        CategoryDTO newCategoryDTO = new CategoryDTO(updateCategoryRequest.getCategoryName(),updateCategoryRequest.getCategoryDescription());
        CategoryDTO oldCategoryDTO = new CategoryDTO("OldTestCategory","Old Test description");

        when(categoryDAO.findById(1L)).thenReturn(Optional.of(oldCategoryDTO));


        when(categoryDAO.save(any(CategoryDTO.class))).thenReturn(newCategoryDTO);
        Category category = categoryService.updateCategory(updateCategoryRequest);

        assertEquals(updateCategoryRequest.getCategoryName(),category.getCategoryName());
        assertEquals(updateCategoryRequest.getCategoryDescription(),category.getCategoryDescription());

        ArgumentCaptor<CategoryDTO> categoryDTOArgumentCaptor = ArgumentCaptor.forClass(CategoryDTO.class);
        verify(categoryDAO, times(1)).save(categoryDTOArgumentCaptor.capture());
        CategoryDTO savedCategoryDTO = categoryDTOArgumentCaptor.getValue();
        assertEquals(updateCategoryRequest.getCategoryName(),savedCategoryDTO.getCategoryName());
        assertEquals(updateCategoryRequest.getCategoryDescription(),savedCategoryDTO.getCategoryDescription());
    }

    @Test
    public void updateCategoryTestWhenIdNotFound() {
        when(categoryDAO.findById(2L)).thenReturn(Optional.empty());
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(2L, "Test Category", "Test Description");
        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(updateCategoryRequest));
    }

    @Test
    public void updateCategoryTestWhenJsonParameterNotValid() {
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(null, "Test Category", "Test Description");
        assertThrows(JsonParameterNotValidException.class,() -> categoryService.updateCategory(updateCategoryRequest));
    }

    @Test
    public void updateCategoryByPatchTestWhenCategoryNull() {
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(2L, null, "Test Description");
        CategoryDTO oldCategoryDTO = new CategoryDTO("Old Test Category", "Old Test Description");
        CategoryDTO newCategoryDTO = new CategoryDTO(oldCategoryDTO.getCategoryName(), updateCategoryRequest.getCategoryDescription());

        when(categoryDAO.findById(2L)).thenReturn(Optional.of(oldCategoryDTO));
        when(categoryDAO.save(any(CategoryDTO.class))).thenReturn(newCategoryDTO);
        Category category = categoryService.updateCategoryByPatch(updateCategoryRequest);

        assertEquals(oldCategoryDTO.getCategoryName(), category.getCategoryName());
        assertEquals(updateCategoryRequest.getCategoryDescription(), category.getCategoryDescription());

        ArgumentCaptor<CategoryDTO> categoryDTOArgumentCaptor = ArgumentCaptor.forClass(CategoryDTO.class);
        verify(categoryDAO,times(1)).save(categoryDTOArgumentCaptor.capture());
        CategoryDTO savedCategory = categoryDTOArgumentCaptor.getValue();
        assertEquals(oldCategoryDTO.getCategoryName(), savedCategory.getCategoryName());
        assertEquals(updateCategoryRequest.getCategoryDescription(), savedCategory.getCategoryDescription());
    }

    @Test
    public void updateCategoryByPatchTestWhenCategoryDescriptionNull() {
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(2L, "Test Category", null);
        CategoryDTO oldCategoryDTO = new CategoryDTO("Old Test Category", "Old Test Description");
        CategoryDTO newCategoryDTO = new CategoryDTO(updateCategoryRequest.getCategoryName(), oldCategoryDTO.getCategoryDescription());

        when(categoryDAO.findById(2L)).thenReturn(Optional.of(oldCategoryDTO));
        when(categoryDAO.save(any(CategoryDTO.class))).thenReturn(newCategoryDTO);
        Category category = categoryService.updateCategoryByPatch(updateCategoryRequest);

        assertEquals(updateCategoryRequest.getCategoryName(), category.getCategoryName());
        assertEquals(oldCategoryDTO.getCategoryDescription(), category.getCategoryDescription());

        ArgumentCaptor<CategoryDTO> categoryDTOArgumentCaptor = ArgumentCaptor.forClass(CategoryDTO.class);
        verify(categoryDAO,times(1)).save(categoryDTOArgumentCaptor.capture());
        CategoryDTO savedCategory = categoryDTOArgumentCaptor.getValue();
        assertEquals(updateCategoryRequest.getCategoryName(), savedCategory.getCategoryName());
        assertEquals(oldCategoryDTO.getCategoryDescription(), savedCategory.getCategoryDescription());
    }

}
