package com.idt.assignment.service;

import com.idt.assignment.model.User;
import com.idt.assignment.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User user = User.builder().id("1234").name("Max").build();

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }


    @Test
    public void whenExistingUserId_thenUserShouldBeFound() {
        User user = userService.getById("1234");
        Assert.assertEquals("Max", user.getName());
    }

}
