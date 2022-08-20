package com.example.shoppro.controllers;

import com.example.shoppro.dtos.UserDto;
import com.example.shoppro.entity.User;

import com.example.shoppro.repositories.CustomUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private  CustomUserService customUserService;


    public UserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @GetMapping(value = "/findall")
    public List<UserDto> fetchAllUsers() {
        return  customUserService.fetchAllUser();
    }


    @PostMapping(value = "/users")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto userDto ) {

        UserDto user1 = customUserService.saveUser(userDto);

        return new ResponseEntity<UserDto>(user1, HttpStatus.CREATED);

    }

    @PatchMapping(value = "/updateuser/{email}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("email") String email, @RequestBody UserDto dto){


        UserDto user1 = customUserService.updateUserInDb(email, dto);

        return new ResponseEntity<UserDto>(user1, HttpStatus.CREATED);

    }


    @GetMapping(value = "/authority/{roleName}")
    public ResponseEntity<List<UserDto>> fetchUserByRole(@PathVariable("roleName") String roleName) {
        List<UserDto> dtos;

        dtos   =  customUserService.fetchUsersByRoles(roleName);
        return ResponseEntity.ok().body(dtos);

    }

    @GetMapping(value = "/userById/{userId}")
    public ResponseEntity<UserDto>    fetchUserById(@Valid @PathVariable("userId")  Integer userId) {
//        String pattern = "^[0-9]*$";


        UserDto user = customUserService.getDtoSingleUserItemById(userId);

        return  new ResponseEntity<UserDto>(user, HttpStatus.OK);

         }



    @PutMapping(value = "/editrole/{userId}/{roleName}")
    public ResponseEntity<?> addRoleToUser(@PathVariable("userId") Integer userId, @PathVariable("roleName")  String roleName){

      UserDto updatedUser = customUserService.addRoleToUser(userId, roleName);


        return  new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping(value = "/users/{userId}")
    public String deleteUserById(@PathVariable("userId") Integer userId) {
        customUserService.deleteUserById(userId);

        return "Deleted Successfully";
    }
    @PostMapping("/users/checkForEmail")
    public String checkForEmail(@Param("email")  String email) {
        return customUserService.isEmailUnique(email) ? "OK" : "DUPLICATE";
    }

    @GetMapping(value = "/usertask/{email}")
        public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {

        User user = customUserService.fetchUserByEmail(email);

        return ResponseEntity.ok().body(user);
    }}
