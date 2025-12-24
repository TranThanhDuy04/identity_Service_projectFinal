package com.devcateria.identityServiceFinal.repository;

import com.devcateria.identityServiceFinal.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
