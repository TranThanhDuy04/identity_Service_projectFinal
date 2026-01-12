package com.devcateria.identity_service_final.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.devcateria.identity_service_final.dto.request.UserCreationRequest;
import com.devcateria.identity_service_final.dto.request.UserUpdateRequest;
import com.devcateria.identity_service_final.dto.response.UserResponse;
import com.devcateria.identity_service_final.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
