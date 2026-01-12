package com.devcateria.identity_service_final.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devcateria.identity_service_final.dto.request.PermissionRequest;
import com.devcateria.identity_service_final.dto.response.PermissionResponse;
import com.devcateria.identity_service_final.entity.Permission;
import com.devcateria.identity_service_final.mapper.PermissionMapper;
import com.devcateria.identity_service_final.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
