package com.devcateria.identity_service_final.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devcateria.identity_service_final.dto.request.RoleRequest;
import com.devcateria.identity_service_final.dto.response.RoleResponse;
import com.devcateria.identity_service_final.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
