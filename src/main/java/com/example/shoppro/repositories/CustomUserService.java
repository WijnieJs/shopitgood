package com.example.shoppro.repositories;


import com.example.shoppro.dtos.UserDto;
import com.example.shoppro.entity.User;

import java.util.List;
import java.util.Optional;

public interface CustomUserService {
        UserDto updateUserInDb(String email, UserDto user);

        List<UserDto> fetchAllUser();


        List<UserDto> fetchUsersByRoles(String roleName);
//     List<User> fetchAllUserz();
        UserDto getDtoSingleUserItemById(Integer userId);

         UserDto saveUser(UserDto dto);


    UserDto addRoleToUser(Integer userId, String roleName);

        void deleteUserById(Integer userId);
        Boolean isEmailUnique(String email);

         User fetchUserByEmail(String email);

}
