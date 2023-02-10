package com.ecommerce.productservices.dao;

import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<ProductDTO, Long> {
}
