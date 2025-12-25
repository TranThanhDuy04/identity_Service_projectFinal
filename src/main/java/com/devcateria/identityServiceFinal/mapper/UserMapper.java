package com.devcateria.identityServiceFinal.mapper;

import com.devcateria.identityServiceFinal.dto.request.UserCreationRequest;
import com.devcateria.identityServiceFinal.dto.request.UserUpdateRequest;
import com.devcateria.identityServiceFinal.dto.response.UserResponse;
import com.devcateria.identityServiceFinal.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);


    UserResponse toUserResponse(User user);


    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
