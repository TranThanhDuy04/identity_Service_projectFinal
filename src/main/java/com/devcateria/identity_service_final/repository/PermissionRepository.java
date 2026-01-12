package com.devcateria.identity_service_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcateria.identity_service_final.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
