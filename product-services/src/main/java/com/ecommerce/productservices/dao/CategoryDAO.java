package com.ecommerce.productservices.dao;

import com.ecommerce.productservices.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<CategoryDTO, Long> {
}
