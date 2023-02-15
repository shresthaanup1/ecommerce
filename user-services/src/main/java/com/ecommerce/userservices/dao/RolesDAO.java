package com.ecommerce.userservices.dao;

import com.ecommerce.userservices.dto.RolesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesDAO extends JpaRepository<RolesDTO, Long> {
}
