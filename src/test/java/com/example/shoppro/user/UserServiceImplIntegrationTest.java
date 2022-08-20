package com.example.shoppro.user;

import com.example.shoppro.dtos.UserDto;
import com.example.shoppro.entity.User;
import com.example.shoppro.exceptions.ProjectNotFoundException;
import com.example.shoppro.exceptions.UserIdException;
import com.example.shoppro.repositories.CustomUserService;
import com.example.shoppro.repositories.RoleRepository;
import com.example.shoppro.repositories.UserRepository;
import com.example.shoppro.services.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplIntegrationTest  {


   @Mock
   private UserRepository userRepository;

   @Mock
   private RoleRepository roleRepository;

   @Mock
   private PasswordEncoder passwordEncoder;

   @InjectMocks
   private UserServiceImpl customUserService;

    @BeforeEach
    public void setUp() {
        /* here goes before-class init logic */

    }

        @Test
        public void testMethod1() {
            String email = "test@example.com";
            String lastName = "lastname";
            Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(
                    new User( email , "John", "Johny", lastName),
                    new User("hwww@mail.com", "west", "derk", "jopp"),
                    new User("aaa@mail.com", "test", "elo", "hopp")
                    ));

        // ACTION
         List<UserDto> allusers = customUserService.fetchAllUser();

            assertThat(allusers.get(0).getEmail()).isEqualTo(email);
            assertThat(allusers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(allusers.size()).isGreaterThan(2);


        }
        @Test
        public void findSingleItem() throws ProjectNotFoundException {
            String lastName = "hopp";
            Mockito.when(userRepository.findById(1)).thenReturn(
                    Optional.of(new User("aaa@mail.com", "test", "elo", "hopp"))
            );

        // ACTION
         UserDto user = customUserService.getDtoSingleUserItemById(1);
         assertEquals(lastName , user.getLastName());

        }
        @Test(expected = UserIdException.class)
        public void findItemNotFound() throws UserIdException {
            Mockito.when(userRepository.findById(1)).thenReturn(
                    Optional.empty());
           UserDto user = customUserService.getDtoSingleUserItemById(1);
        }
        @Test
        public void saveNewuser() throws ProjectNotFoundException {

    }

}

