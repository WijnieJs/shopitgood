package com.example.shoppro.repositories;

//import com.example.shoppro.dtos.UserDto;

import com.example.shoppro.dtos.UserDto;
import com.example.shoppro.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
//    @Query("SELECT u FROM User u WHERE u.email = :email")

    User getUserByEmail(String email);




}


