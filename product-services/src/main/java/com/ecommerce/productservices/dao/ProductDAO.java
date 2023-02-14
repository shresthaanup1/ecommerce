package com.ecommerce.productservices.dao;

import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<ProductDTO, Long> {
    @Query(value = "SELECT * FROM Product p WHERE p.category_id = ?1",nativeQuery = true)
    List<ProductDTO> findByCategoryId(Long categoryId);
}
