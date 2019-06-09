package com.idt.assignment.mapper;

import com.idt.assignment.dto.UserDto;
import com.idt.assignment.model.User;
import org.mapstruct.Mapper;

/**
 * Mapper for user model
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto user);
    UserDto toDto(User user);

}
