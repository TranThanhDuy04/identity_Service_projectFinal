package com.devcateria.identityServiceFinal.mapper;

import com.devcateria.identityServiceFinal.dto.request.PermissionRequest;
import com.devcateria.identityServiceFinal.dto.response.PermissionResponse;
import com.devcateria.identityServiceFinal.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

}
