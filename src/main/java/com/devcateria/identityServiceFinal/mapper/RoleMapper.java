package com.devcateria.identityServiceFinal.mapper;

import com.devcateria.identityServiceFinal.dto.request.PermissionRequest;
import com.devcateria.identityServiceFinal.dto.request.RoleRequest;
import com.devcateria.identityServiceFinal.dto.response.PermissionResponse;
import com.devcateria.identityServiceFinal.dto.response.RoleResponse;
import com.devcateria.identityServiceFinal.entity.Permission;
import com.devcateria.identityServiceFinal.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);

}
