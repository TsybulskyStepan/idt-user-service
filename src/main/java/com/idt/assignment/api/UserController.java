package com.idt.assignment.api;

import com.idt.assignment.dto.UserDto;
import com.idt.assignment.mapper.UserMapper;
import com.idt.assignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * User controller for user management
 */
@Validated
@RestController
@RequestMapping(path = "/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    /**
     * Provides an ability to retrieve user by its id
     * @param id id of the requested user
     * @return found user
     */
    @GetMapping(value = "/{id}")
    public UserDto getById(@PathVariable String id) {
        return userMapper.toDto(userService.getById(id));
    }

    /**
     * Updates user by its id
     * @param id user id
     * @param user updated user information
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateById(@PathVariable String id, UserDto user) {
        userService.updateById(id, userMapper.toEntity(user));
    }

    /**
     * Fetch existing users
     *
     * @return found users
     */
    @GetMapping
    public Collection<UserDto> getAll() {
        return StreamSupport.stream(userService.findAll().spliterator(), true).map(userMapper::toDto).collect(Collectors.toList());
    }

}
