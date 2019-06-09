package com.idt.assignment.api;

import com.idt.assignment.dto.UserDto;
import com.idt.assignment.mapper.UserMapper;
import com.idt.assignment.model.User;
import com.idt.assignment.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(secure = false)
public class UserControllerTest {

    @TestConfiguration
    static class Config {
        @Bean
        public UserMapper mapper() {
            return new UserMapper() {
                @Override
                public User toEntity(UserDto user) {
                    return User.builder().id(user.getId()).name(user.getName()).build();
                }

                @Override
                public UserDto toDto(User user) {
                    return UserDto.builder().id(user.getId()).name(user.getName()).build();
                }
            };
        }
    }

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    public UserService userService;

    @Before
    public void before() {
        when(userService.getById("test")).thenReturn(User.builder().id("test").name("Alex").build());
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/users/test"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Alex")));
    }

}
