package com.example.shoppro.user;


import com.example.shoppro.controllers.UserController;
import com.example.shoppro.dtos.UserDto;
import com.example.shoppro.entity.User;
import com.example.shoppro.exceptions.ProjectNotFoundException;
import com.example.shoppro.repositories.CustomUserService;
import com.example.shoppro.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Mock
    private UserRepository userRepository;

    @MockBean
    private CustomUserService customUserService;



    @Test
    public void findAllUsers() throws Exception {

        when(customUserService.fetchAllUser()).thenReturn(Arrays.asList(
                new UserDto(11 , "xxxx@mail.com", "test", "www", "hopp"),
                new UserDto(12 , "bbb@mail.com", "quest", "yyy", "hee"),
                new UserDto(1 , "aaa@mail.com", "eee", "nnyy", "dd")
        ));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/findall"))
                .andExpect(content().json("[{}, {}, {}]"));

    }
    @Test
    public void findAllEmpty() throws Exception {
            when(customUserService.fetchAllUser()).thenReturn(Collections.emptyList());
            // Action

            mockMvc.perform(
                    MockMvcRequestBuilders.get("/findall"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[]"));
    }

}
