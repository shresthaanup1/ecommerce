package com.ecommerce.productservices;

import com.ecommerce.productservices.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<CategoryDTO,Long> {
}
