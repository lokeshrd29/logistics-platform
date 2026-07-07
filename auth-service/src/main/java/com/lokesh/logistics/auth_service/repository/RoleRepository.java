package com.lokesh.logistics.auth_service.repository;

import com.lokesh.logistics.auth_service.entity.Role;
import com.lokesh.logistics.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRole(String role);

}
