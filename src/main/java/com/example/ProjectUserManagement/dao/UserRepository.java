package com.example.ProjectUserManagement.dao;

import com.example.ProjectUserManagement.api.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
