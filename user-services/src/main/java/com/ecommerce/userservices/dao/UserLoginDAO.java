package com.ecommerce.userservices.dao;

import com.ecommerce.userservices.dto.UserLoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginDAO extends JpaRepository<UserLoginDTO, Long> {
}
