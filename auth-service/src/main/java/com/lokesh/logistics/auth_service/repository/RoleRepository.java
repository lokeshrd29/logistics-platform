package com.lokesh.logistics.auth_service.repository;

import com.lokesh.logistics.auth_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
