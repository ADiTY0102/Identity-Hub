package com.backend.identityhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.identityhub.dto.response.UserResponseDTO;
import com.backend.identityhub.entity.UserEntity;

@Mapper(config = CommonMapperConfig.class)

public interface UserMapper {

    @Mapping(target = "email", source = "email")
    UserResponseDTO toResponse(UserEntity entity);

}
