package com.rutrob.recipesapi.mappers;

import com.rutrob.recipesapi.entities.User;
import com.rutrob.recipesapi.rest.dto.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User signUpRequestToUser(SignUpRequest signUpRequest);
}
