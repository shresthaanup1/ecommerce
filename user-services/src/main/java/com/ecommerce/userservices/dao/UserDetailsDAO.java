package com.ecommerce.userservices.dao;

import com.ecommerce.userservices.dto.UserDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsDAO extends JpaRepository<UserDetailsDTO, Long>{
}
