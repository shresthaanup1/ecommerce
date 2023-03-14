package com.ecommerce.productservices.controller;

import com.ecommerce.productservices.exception.ErrorResponse;
import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.model.UpdateCategoryRequest;
import com.ecommerce.productservices.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/product")
@Tag(name="Categories",description = "For managing different categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    //@RequestMapping(value = "/category", method = RequestMethod.POST)
    @Operation(summary="Adds new category",description = "Adds new category and returns the added role with newly generated id", security =  {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses( value= {
            @ApiResponse(responseCode = "201", description = "OK.Successful addition of category", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Category.class)))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping(value = "/category",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> addCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest) {
        return new ResponseEntity(categoryService.addCategory(addCategoryRequest), HttpStatus.CREATED);
    }

    @Operation(summary="Retrieves all existing categories",description = "Retrieves all existing categories and gives list")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of categories", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Category.class))))
    @GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> listCategories() {
        return new ResponseEntity<>(categoryService.listCategories(), HttpStatus.OK);
    }

    @Operation(summary="Retrieves existing category",description = "Retrieves the existing category based on id provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "A category with the specified ID was not found.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "302", description = "Successful retrieval of category", content = @Content(schema = @Schema(implementation = Category.class))),
    })
    @GetMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        LOGGER.info("CategoryId:" + id);
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.FOUND);
    }

    @Operation(summary="Deletes existing category",description = "Deletes the existing category based on id provided",security = { @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "A category with the specified ID was not found.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "204", description = "Successful deletion of category", content = @Content(schema = @Schema(implementation = Category.class))),
    })
    @DeleteMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary="Updates existing category",description = "Updates the existing category based on id provided", security = { @SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "A category with the specified ID was not found.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "OK.Successful update of category", content = @Content(schema = @Schema(implementation = Category.class))),
    })
    @PutMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return new ResponseEntity<>(categoryService.updateCategory(updateCategoryRequest), HttpStatus.OK);
    }

    @Operation(summary="Updates existing category",description = "Updates the existing category based on id and fields provided for update. This will only update those fields which are provided in the request body",security = { @SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "A category with the specified ID was not found.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "OK.Successful update of category", content = @Content(schema = @Schema(implementation = Category.class))),
    })
    @PatchMapping(value = "/category")
    public ResponseEntity<Category> updateCategoryByPatch(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return new ResponseEntity<>(categoryService.updateCategoryByPatch(updateCategoryRequest), HttpStatus.OK);
    }

}
