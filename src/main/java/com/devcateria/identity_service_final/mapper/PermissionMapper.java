package com.devcateria.identity_service_final.mapper;

import org.mapstruct.Mapper;

import com.devcateria.identity_service_final.dto.request.PermissionRequest;
import com.devcateria.identity_service_final.dto.response.PermissionResponse;
import com.devcateria.identity_service_final.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
