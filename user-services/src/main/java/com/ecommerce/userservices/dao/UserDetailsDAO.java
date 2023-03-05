package com.ecommerce.userservices.dao;

import com.ecommerce.userservices.dto.UserDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsDAO extends JpaRepository<UserDetailsDTO, Long>{
    Optional<UserDetailsDTO> findByUserId(String userId);
}
