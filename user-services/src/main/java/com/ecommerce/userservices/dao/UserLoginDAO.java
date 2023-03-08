package com.ecommerce.userservices.dao;

import com.ecommerce.userservices.dto.UserLoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLoginDAO extends JpaRepository<UserLoginDTO, Long> {
    @Query(value = "SELECT * FROM user_login u WHERE u.user_details_id = ?1",nativeQuery = true)
    UserLoginDTO findByUserId(Long userId);
}
